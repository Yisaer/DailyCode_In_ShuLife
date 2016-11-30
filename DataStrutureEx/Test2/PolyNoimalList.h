//
// Created by 高松 on 2016/11/30.
//

#ifndef TEST2_POLYNOIMALLIST_H
#define TEST2_POLYNOIMALLIST_H
#include "Noimal.h"

class PolyNoimalList{
private:
    Noimal * head;
public:
    PolyNoimalList(){
        head = NULL;
    }
    /*
     * 得到尾指针
     */
    Noimal* getTail(){
        if(head ==NULL){
            return NULL;
        }
        Noimal * pre = NULL;
        for(Noimal * cur = head ; cur!=NULL ; cur =  cur->getPtr()){
            pre = cur;
        }
        return pre;

    }

    void addNoimal(Noimal * refNoimal){
        /*
         * 链表为空时
         */
        if(head ==NULL ){
            head = refNoimal;
            return;
        }
        /*
         * 当链表非空，从高到低插入结点
         */
        Noimal* pre = NULL;
        bool IsFind  = false;
        for(Noimal * cur = head ; cur !=NULL;cur = cur->getPtr()){
            /*
             * 找到位置并比cur项高阶
             */
            if(refNoimal->getExpo()>cur->getExpo()){
                IsFind = true;
                /*
                 * refNoimal是最大项
                 */
                if(cur == head){
                    refNoimal->setPtr(head);
                    head = refNoimal;
                    return;
                }
                /*
                 * 插入链表当中
                 */
                pre->setPtr(refNoimal);
                refNoimal->setPtr(cur);
            }
               /*
                * 同阶
                */
            else if(refNoimal->getExpo() == cur->getExpo()){
                IsFind = true;
                /*
                 * 系数和为0
                 */
                if(cur->getCoef()+refNoimal->getCoef() == 0){
                    pre->setPtr(cur->getPtr());
                    refNoimal->DeleteSelf();
                    cur->DeleteSelf();
                }
                else{
                    /*
                     * 系数和非0
                     */
                    cur->setCoef(cur->getCoef()+refNoimal->getCoef());
                    refNoimal->DeleteSelf();
                }

            }
            pre = cur ;
        }
        if(IsFind == false){
            Noimal * tail = getTail();
            if(tail != NULL){
                tail->setPtr(refNoimal);
            }
        }
    }

    void TransPrint(){
        for(Noimal* cur = head ; cur !=NULL ; cur = cur->getPtr()){
            cout<<"Coef = "<<(cur->getCoef())<<" Expo = "<<(cur->getExpo())<<endl;
        }
    }
    void setHead(Noimal* head){
        this->head = head;
    }
    Noimal* getHead(){
        return head;
    }


};

#endif //TEST2_POLYNOIMALLIST_H
