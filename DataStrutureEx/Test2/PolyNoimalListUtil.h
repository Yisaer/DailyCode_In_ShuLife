//
// Created by 高松 on 2016/11/30.
//

#ifndef TEST2_POLYNOIMALLISTUTIL_H
#define TEST2_POLYNOIMALLISTUTIL_H
#include "PolyNoimalList.h"

#include <vector>


class PolyNoimalListUitl{
private:
    vector<Noimal*> curPtrArray;
public:
    PolyNoimalListUitl(vector<Noimal*> refPtrArray){
        vector<Noimal*>::iterator iter;
        for(iter = refPtrArray.begin();iter!=refPtrArray.end();iter++){
            curPtrArray.push_back(*iter);
        }
    }
    PolyNoimalList* AddTwolist(Noimal * Node1,Noimal* Node2) {
        PolyNoimalList *resList = NULL;
        resList = new PolyNoimalList();
        while(Node1 != NULL || Node2 !=NULL){
            if(Node1 ==NULL){
                cout<<"Node1 is Empty"<<endl;
                if(resList !=NULL){
                    resList->getTail()->setPtr(Node2);
                }
                else{
                    resList->setHead(Node2);
                }
                break;
            }
            if(Node2 ==NULL){

                if(resList !=NULL){
                    resList->getTail()->setPtr(Node1);
                }
                else{
                    resList->setHead(Node1);
                }
                break;
            }
            if(Node1->getExpo() > Node2->getExpo()){
                Noimal* tmp = Node1;
                Node1 = Node1->getPtr();
                Noimal * NewNode = new Noimal(tmp->getCoef(),tmp->getExpo());
                resList->addNoimal(NewNode);
            }
            else if(Node1->getExpo() == Node2->getExpo()){

                Noimal* tmp1 = Node1;
                Noimal* tmp2 = Node2;
                Node1 = Node1->getPtr();
                Node2 = Node2->getPtr();
                if(tmp1->getCoef()+tmp2->getCoef()){
                    Noimal* NewNode = new Noimal(tmp1->getCoef()+tmp2->getCoef(),tmp1->getExpo());
                    resList->addNoimal(NewNode);
                }
            }
            else if(Node1->getExpo() < Node2->getExpo()){
                Noimal* tmp = Node2;
                Node2 = Node2->getPtr();
                Noimal * NewNode = new Noimal(tmp->getCoef(),tmp->getExpo());
                resList->addNoimal(NewNode);
            }
        }
        return resList;
    }
    PolyNoimalList* MultiTwoList(Noimal* Node1,Noimal * Node2){
        PolyNoimalList* resList = NULL;
        resList = new PolyNoimalList();

        while(Node2 !=NULL){

            for(Noimal* cur = Node1;cur!=NULL;cur=cur->getPtr()){

                Noimal* NewNode = NULL;
                NewNode = new Noimal(Node2->getCoef()*cur->getCoef(),Node2->getExpo()*cur->getExpo());
                resList->addNoimal(NewNode);
            }
            Node2 = Node2->getPtr();
        }
        return resList;
    }
    PolyNoimalList* AddAllList(){
//        PrintVector();
        Noimal* Node1 = curPtrArray[0];
        PolyNoimalList* res = NULL;
        for(int  i =1;i<curPtrArray.size();i++){
            res = AddTwolist(Node1,curPtrArray[i]);
            Node1 = res->getHead();
        }
        return res;
    }
    PolyNoimalList* MutiAllList(){
//        PrintVector();

        Noimal* Node1 = curPtrArray[0];
        PolyNoimalList* res = NULL;
        for(int  i =1;i<curPtrArray.size();i++){
            res = MultiTwoList(Node1,curPtrArray[i]);
            Node1 = res->getHead();
        }
        return res;
    }

    vector<Noimal* > getNoimal(){
        return curPtrArray;
    }
    void PrintVector(){
        for(int i = 0;i<curPtrArray.size();i++){
            cout<<" Index = "<<curPtrArray[i]<<endl;
        }
    }


    void  addListToOne(){
        PolyNoimalList* resList = NULL;
        resList = new PolyNoimalList();
    }

};


#endif //TEST2_POLYNOIMALLISTUTIL_H
