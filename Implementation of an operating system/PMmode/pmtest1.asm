; ==========================================

; pmtest1.asm

; 编译方法：nasm pmtest1.asm -o pmtest1.com

;去掉了段定义，以用于判断512字节处加上55AA标记

; ==========================================

 

%include	"pm.inc"	; 常量, 宏, 以及一些说明

 

org	0100h

jmp	LABEL_BEGIN

 

; GDT

;                                         段基址,      段界限     , 属性

LABEL_GDT:	Descriptor	      0,                0, 0     ; 空描述符

LABEL_DESC_CODE32:	Descriptor	      0, SegCode32Len - 1, DA_C + DA_32	; 非一致代码段, 32

LABEL_DESC_VIDEO:	Descriptor	0B8000h,           0ffffh, DA_DRW	; 显存首地址

; GDT 结束

 

GdtLen	equ	$ - LABEL_GDT	; GDT长度

GdtPtr	dw	GdtLen - 1	; GDT界限

dd	0	; GDT基地址

 

; GDT 选择子

SelectorCode32	equ	LABEL_DESC_CODE32	- LABEL_GDT

SelectorVideo	equ	LABEL_DESC_VIDEO	- LABEL_GDT

 

 

[BITS	16]

LABEL_BEGIN:

mov	ax, cs

mov	ds, ax

mov	es, ax

mov	ss, ax

mov	sp, 0100h

 

; 初始化 32 位代码段描述符

xor	eax, eax

mov	ax, cs

shl	eax, 4

add	eax, LABEL_SEG_CODE32

mov	word [LABEL_DESC_CODE32 + 2], ax

shr	eax, 16

mov	byte [LABEL_DESC_CODE32 + 4], al

mov	byte [LABEL_DESC_CODE32 + 7], ah

 

; 为加载 GDTR 作准备

xor	eax, eax

mov	ax, ds

shl	eax, 4

add	eax, LABEL_GDT	; eax <- gdt 基地址

mov	dword [GdtPtr + 2], eax	; [GdtPtr + 2] <- gdt 基地址

 

; 加载 GDTR

lgdt	[GdtPtr]

 

; 关中断

cli

 

; 打开地址线A20

in	al, 92h

or	al, 00000010b

out	92h, al

 

; 准备切换到保护模式

mov	eax, cr0

or	eax, 1

mov	cr0, eax

 

; 真正进入保护模式 这里修改了一下代码，试一下带偏移量的跳转

jmp	word SelectorCode32:OFFSET_32	; 执行这一句会把 SelectorCode32 装入 cs, 并跳转到 Code32Selector:0  处

 

 

[BITS	32]

 

LABEL_SEG_CODE32:

 

times 100 db 0 ;加一些无意义的代码，用于测试带便宜量的跳转

LABEL_START:

    OFFSET_32	equ	LABEL_START	- LABEL_SEG_CODE32

mov	ax, SelectorVideo

mov	gs, ax	; 视频段选择子(目的)

 

mov	edi, (80 * 10 + 0) * 2	; 屏幕第 10 行, 第 0 列。

mov	ah, 0Ch	; 0000: 黑底    1100: 红字

mov	al, 'P'

mov	[gs:edi], ax

 

; 到此停止

jmp	$

 

SegCode32Len	equ	$ - LABEL_SEG_CODE32

 

times 510-($-$$)	db	0	; 填充剩下的空间，使生成的二进制代码恰好为210字节

dw 0xaa55	; 结束标志
