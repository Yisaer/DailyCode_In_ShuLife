;==========================
; pmtest2.asm
; 编译方法： nasm pmtest2
;===========================

%include "pm.inc"   ;常量，宏，说明

org 0100H	; COM 文件的起始偏移地址
    jmp LABEL_BEGIN

[SECTION .gdt]
; GDT
;                            段基址,        段界限 , 属性
LABEL_GDT:         Descriptor    0,              0, 0         ; 空描述符
LABEL_DESC_NORMAL: Descriptor    0,         0ffffh, DA_DRW    ; Normal 描述符
LABEL_DESC_CODE32: Descriptor    0, SegCode32Len-1, DA_C+DA_32; 非一致代码段, 32
LABEL_DESC_CODE16: Descriptor    0,         0ffffh, DA_C      ; 非一致代码段, 16
LABEL_DESC_DATA:   Descriptor    0,      DataLen-1, DA_DRW    ; Data
LABEL_DESC_STACK:  Descriptor    0,     TopOfStack, DA_DRWA+DA_32; Stack, 32 位
LABEL_DESC_TEST:   Descriptor 0500000h,     0ffffh, DA_DRW
LABEL_DESC_VIDEO:  Descriptor  0B8000h,     0ffffh, DA_DRW    ; 显存首地址
; GDT 结束

GdtLen 	equ $-LABEL_GDT;
GdtPtr	dw	GdtLen-1;	Gdt界限
	dd	0;		Gdt基地址


; GDT 选择子
SelectorNormal		equ	LABEL_DESC_NORMAL	- LABEL_GDT
SelectorCode32		equ	LABEL_DESC_CODE32	- LABEL_GDT
SelectorCode16		equ	LABEL_DESC_CODE16	- LABEL_GDT
SelectorData		equ	LABEL_DESC_DATA		- LABEL_GDT
SelectorStack		equ	LABEL_DESC_STACK	- LABEL_GDT
SelectorTest		equ	LABEL_DESC_TEST		- LABEL_GDT
SelectorVideo		equ	LABEL_DESC_VIDEO	- LABEL_GDT
; END of [SECTION .gdt]


[SECTION .data1]	 ; 数据段
ALIGN	32
[BITS	32]
LABEL_DATA:
SPValueInRealMode	dw	0
; 字符串
PMMessage:		db	"In Protect Mode now. ^-^", 0	; 在保护模式中显示
OffsetPMMessage		equ	PMMessage - $$
StrTest:		db	"ABCDEFGHIJKLMNOPQRSTUVWXYZ", 0
OffsetStrTest		equ	StrTest - $$
DataLen			equ	$ - LABEL_DATA
; END of [SECTION .data1]


; 全局堆栈段
[SECTION .gs]
ALIGN 32
[BITS 32]
LABEL_STACK:	times 512 db 0 

TopOfStack	equ	$ - LABEL_STACK -1

; END of [SECTION .gs]  堆栈段结束


; 实模式启动， 从16位代码 开始
[SECTION .s16]
[BITS 16]
LABEL_BEGIN:
	mov	ax, cs
	mov	ds, ax
	mov	es, ax
	mov	ss, ax
	mov	sp, 0100h

	mov	[LABEL_GO_BACK_TO_REAL+3], ax
	mov [SPValueInRealMode],sp

	; 初始化16位代码段描述符
	mov ax,cs
	movzx eax,ax
	shl eax,4
 	add eax,LABEL_DESC_CODE16	
	mov word [LABEL_DESC_CODE16+2],ax
	shr eax,16
	mov byte [LABEL_DESC_CODE16+4],al
	mov byte [LABEL_DESC_CODE16+7],ah

	;初始化32位代码
	xor eax,eax
	mov ax,cs
	shl eax,4
	add eax,LABEL_SEG_CODE32
	mov word [LABEL_DESC_CODE32+2],ax
	shr eax,16
	mov byte [LABEL_DESC_CODE32+4],al
	mov byte [LABEL_DESC_CODE32+7],ah

	;初始化数据段描述符
	xor eax,eax
	mov ax,ds
	shl eax,4
	add eax ,LABEL_DATA
	mov word [LABEL_DESC_DATA+2],ax
	shr eax,16
	mov byte [LABEL_DESC_DATA+4],al
	mov byte [LABEL_DESC_DATA+7],ah

	;初始化堆栈段描述符
	xor eax,eax
	mov ax,ds
	shl eax,4
	add eax,LABEL_STACK
	mov word [LABEL_DESC_STACK+2],ax
	shr eax,16
	mov byte [LABEL_DESC_STACK+4],al
	mov byte [LABEL_DESC_STACK+7],ah

	;为加载GDTR作准备
	xor eax,eax
	mov ax,ds
	shl eax,4
	add eax,LABEL_GDT	; eax<-gdt基地址
	mov dword [GdtPtr+2],eax; [GdtPtr+2] <- gdt基地址
	
	;加载GDTR
	lgdt [GdtPtr]
	
	;关中断
	cli
	
	;打开地址线
	in al,92H
	or al,00000010B
	out 92H,al

	;切换到保护模式的准备
	mov eax,cr0
	or eax,1
	mov cr0,eax

	;进入保护模式
	jmp dword SelectorCode32:0
	
;==================================================
;从保护模式跳回到实模式

LABEL_REAL_ENTRY:	
	mov ax,cs
	mov ds,ax
	mov es,ax
	mov ss,ax
	mov sp,[SPValueInRealMode]
	
	in al,92H
	and al,11111101B ;关闭A20总线
	sti	;关中断
	mov ax,4C00H
	int 21H
;End of [SECTION .s16]


;==================================================

SECTION .s32];	32位代码段，由实模式跳入
[BITS 32]

LABEL_SEG_CODE32:
	mov ax,SelectorData
	mov ds,ax		;数据段测试子
	mov ax,SelectorTest	;
	mov es,ax		;测试段选择子
	mov ax,SelectorVideo	;
	mov gs,ax		;视频段选择子，段寄存器gs
	
	mov ax,SelectorStack
	mov ss,ax		;堆栈选择子
	mov esp,TopOfStack

	;显示字符串
	mov ah,0Ch	;0000:黑底 1100红字
	xor esi,esi
	xor edi,edi	
	mov esi,OffsetPMMessage	;源数据的偏移
	mov edi,(80*10+0)*2	;目的数据偏移，10行0列
	cld

.1:
	lodsb	; lodsb、lodsw：把DS:SI指向的存储单元中的数据装入AL或AX，然后根据DF标志增减SI
	test al,al
	jz .2
	mov [gs:edi],ax
	add edi,2
	jmp .1
.2:	;显示完毕

	call DispReturn
	call TestRead
	call TestWrite
	call TestRead
	
	;到此停止
	jmp SelectorCode16:0
;==========================================================
TestRead:
	xor	esi,esi
	mov ecx,8
.loop:
	mov al,[es:esi]
	call DispAL
	inc esi
	loop .loop

	call DispReturn
	ret

;TestRead结束================================================

;===========================================================
TestWrite:
	push esi
	push edi
	xor esi,esi
	xor edi,edi
	mov esi,OffsetStrTest	;源数据偏移
	cld
.1:
	lodsb
	test	al,al
	jz	.2
	mov [es:edi],al
	inc edi
	jmp .1
.2:
	pop edi
	pop esi
	ret
;TestWrite结束============================================

;=======================================================
;显示AL中的数字
;默认：
;	数字已经在AL中
;	edi始终指向要显示的下一个字符的位置
;被改变的寄存器： ax,edi
;=======================================================

DispAL:
	push ecx
	push edx

	mov ah,0Ch
	mov dl,al
	shr al,4
	mov ecx,2
.begin:
	and AL,01111B
	cmp al,9
	ja  .1
	add al,'0'
	ja  .1
	add al,'0'
	jmp .2
.1:
	sub al,0AH
	add al,'A'
.2:
	mov [gs:edi],ax
	add edi,2
	
	mov al,dl
	loop .begin
	add edi,2
	
	pop edx
	pop ecx
	ret
;DispAL结束==================================================


;===========================================================

;模拟回车的显示
;=============================================================
DispReturn:
	push eax
	push ebx
	mov eax,edi
	mov bl,160
	div bl
	and eax,0FFH
	inc eax
	mov bl,160
	mul bl
	mov edi,eax
	pop ebx
	pop eax
	ret
;DispReturn结束==============================================
SegCode32Len equ $-LABEL_SEG_CODE32
;END of [Section .32]

;16位代码段 .由32为代码段跳入，跳出后到实模式
[SECTION  .s16code]
ALIGN 32
[BITS 16]
LABEL_SEG_CODE16:
	;跳回实模式；
	mov ax,SelectorNormal
	mov ds,ax
	mov es,ax
	mov fs,ax
	mov gs,ax
	mov ss,ax
	
	mov eax,cr0
	and al,11111110B
	mov cr0,eax

LABEL_GO_BACK_TO_REAL:
	jmp	0:LABEL_REAL_ENTRY	; 段地址会在程序开始处被设置成正确的值

Code16Len	equ	$ - LABEL_SEG_CODE16
;END OF [SECTION.s16code]
























































































































































	

