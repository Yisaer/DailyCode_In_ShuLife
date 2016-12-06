//
// Created by 高松 on 2016/12/6.
//

#ifndef CC1_3_SEQ_H
#define CC1_3_SEQ_H
#include "Node.h"
class Seq{
public:
    Node Arr[200];
    int s;
    int e;
    int step;
    Seq(){
        s = 1;
        e = 0;
        step = 1;
    }
    void Append(Node* node){
        e++;
        Arr[e].setval(node->getVal());
    }

    void Print(){
        bool flag = true;
        for(int i = s; flag== true; i += step){
            cout<<Arr[i].getVal()<<" ";
            if(i == e){
                flag = false;
            }
        }cout<<endl;
    }

};

#endif //CC1_3_SEQ_H
