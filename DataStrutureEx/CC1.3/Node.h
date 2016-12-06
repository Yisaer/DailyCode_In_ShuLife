//
// Created by 高松 on 2016/12/6.
//

#ifndef CC1_3_NODE_H
#define CC1_3_NODE_H

#include <iostream>
using namespace std;


class Node{
private:
    int val;
public:
    Node* TB[2];
    void setval(int val){
        this->val = val;
    }
    Node(){
        val = 0;
        TB[0] =NULL;
        TB[1] =NULL;
    }
    Node(int val){
        this->val = val;
        TB[0] =NULL;
        TB[1] =NULL;
    }
    int getVal(){
        return val;
    }

};

#endif //CC1_3_NODE_H
