//
// Created by 高松 on 2016/11/30.
//

#ifndef TEST2_POLYNOIMALSERVICE_H
#define TEST2_POLYNOIMALSERVICE_H

#include "PolyNoimalListUtil.h"

class PolyNoimalService{

private:
    PolyNoimalListUitl* polyNoimalListUitl ;
public:
    PolyNoimalService(){
        vector<Noimal*> arr;
        cout<<"输入链表个数"<<endl;
        int n;cin>>n;
        PolyNoimalList* polyListArray[n];
        for(int i = 0;i<n;i++){
            polyListArray[i] = NULL;
            polyListArray[i] = new PolyNoimalList();
        }
        for(int i = 0;i<n;i++){
            cout<<"输入第"<<i+1<<"个链表的参数 以coef expo的方式，当expo =-1时输入停止"<<endl;
            int coef,expo;
            do{
                cin>>coef>>expo;
                if(expo == -1){
                    break;
                }
                if(coef==0){
                    cout<<"系数请勿为0"<<endl;
                    continue;
                }
                Noimal* NewNode  = new Noimal(coef,expo);
                polyListArray[i]->addNoimal(NewNode);
            }while(true);
        }
        for(int i =0;i<n;i++){
            arr.push_back(polyListArray[i]->getHead());
        }
        polyNoimalListUitl = new PolyNoimalListUitl(arr);
    }
    PolyNoimalList* addAllToOne(){
        return polyNoimalListUitl->AddAllList();
    }
    PolyNoimalList* MultiAllToOne(){
        return polyNoimalListUitl->MutiAllList();
    }
    void PrintOneList(PolyNoimalList* polyNoimalList){
        polyNoimalList->TransPrint();
    }
};

#endif //TEST2_POLYNOIMALSERVICE_H
