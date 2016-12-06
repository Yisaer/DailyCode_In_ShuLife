//
// Created by 高松 on 2016/12/6.
//

#ifndef CC1_3_LIST_H
#define CC1_3_LIST_H

#include "Node.h"

class List{
private:
    Node*  head;
    Node*  tail;
    int i;
public:
    List(){

        i = 1;
        head =NULL;
        tail = NULL;
    }
    int geti(){
        return i;
    }

    void seti(int i){
        this->i = i;
    }
    Node* getHead(){
        return head;
    }
    Node* gettail (){
        return tail;
    }
    void setHead(Node* head){
        this->head= head;
    }
    void setTail(Node* tail){
        this->tail = tail;
    }
    void append(Node* node){
        if(head ==NULL && tail ==   NULL){
            head = tail = node;
            node->TB[0] = NULL;
            node->TB[1] = NULL;
        }
        else{
            tail->TB[1] =node;
            node->TB[0] = tail;
            node->TB[1]= NULL;
            tail = node;
        }
    }
    void Print(){
        for(Node* cur = head ; cur !=NULL ; cur = cur->TB[i]){
            cout<<cur->getVal()<<" ";
        }   cout<<endl;
    }
};


#endif //CC1_3_LIST_H
