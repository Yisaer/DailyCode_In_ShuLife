//
// Created by 高松 on 2017/1/3.
//

#ifndef P11_NODE_H
#define P11_NODE_H
#include <string>


struct Node{
    Node* lson;
    Node* rson;
    string data;
    Node(string str){
        data = str;
        lson = rson = NULL;
    }
    void setlson(Node* lson){
        this->lson = lson;
    }
    void setrson(Node* rson){
        this->rson = rson;
    }
};


#endif //P11_NODE_H
