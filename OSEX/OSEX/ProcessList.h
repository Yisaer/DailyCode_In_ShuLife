//
// Created by Yisa on 2016/11/29.
//

#ifndef OSEX1_PROCESSLIST_H
#define OSEX1_PROCESSLIST_H
#include "ProcessControBlock.h"
#include <cstdio>
#include <algorithm>
using namespace std;


int static Algo_RoundRobin = 1;
int static Algo_Priority = 2;

struct X {
    int ID;
    int PRIORITY;
    int CURTIME;
    int ALLTIME;
    int STATE;
    int NEXT;
};

bool cmp(X a, X b){
    return a.ID<b.ID;
}
class ProcessList{
private:
    ProcessControlBlock* head;
    ProcessControlBlock* tail;
    ProcessControlBlock* Ftail;
    int Algo;
    int ListLength;



protected:
    int getAlgo(){
        return Algo;
    }
public:
    void setListLength(int len){
        ListLength = len;
    }
    int getListLengthn(){
        return ListLength;
    }
    void TransList(){
        for(ProcessControlBlock* cur = head;cur!=NULL;cur=cur->getPtr()){
            cout<<"ID = "<<cur->getProcessIndex()<<" Pri = "<<cur->getPriorityNum()<<" CUR = "<<cur->getCurrentTime();
            cout<<" ALLTIME = "<<cur->getAllTime();
            cout<<" Next = ";
            if(cur->getPtr()!=NULL){
                cout<<cur->getPtr()->getProcessIndex();
            }
            else{
                cout<<0;
            }
            cout<<" STATE = ";
            if(cur->getState() == State_Running){
                cout<<"R";
            }
            if(cur->getState() == State_Waiting){
                cout<<"W";
            }
            if(cur->getState() == State_Finish){
                cout<<"F";
            }
            cout<<" ptr = "<<cur->getPtr();
            cout<<endl;
        }
        cout<<"head = "<<head->getProcessIndex()<<" tail = "<<tail->getProcessIndex()<<" Ftail = "<<(Ftail==NULL?0:(Ftail->getProcessIndex()))<<endl;
        cout<<endl;
    }
    ProcessList(int algo){
        head = NULL;
        tail = NULL;
        Algo = algo;
        Ftail = NULL;
        ListLength = 0;
    }

    bool IsRunning(){

        if(head->getState() == State_Waiting){
            head->setState(State_Running);
            return true;
        }
        else{

            return false;
        }
    }

    void addProcess(ProcessControlBlock* curProcessPtr){

        /*
         * When List is Empty
         */
        if(head == NULL && tail == NULL){
            head = curProcessPtr;
            tail = curProcessPtr;
            return;
        }
        /*
         * When Algo is RoundRobin
         */
         if(Algo == Algo_RoundRobin){
             tail->setPtr(curProcessPtr);
             tail = curProcessPtr;
             curProcessPtr->setPtr(NULL);
         }
         /*
          * When Algo is Priority
          * Insert with Priority
          */
        else if( Algo == Algo_Priority){
             bool isFind = false;
             ProcessControlBlock* cur = NULL;
             ProcessControlBlock* pre = NULL;
             for(cur = head ;cur!=NULL ; cur = cur->getPtr()){
                 if(curProcessPtr->getPriorityNum() > cur->getPriorityNum()){
                     /*
                      *  If refProcess's Priority above the curProcess'Priority
                      *  When curProcess is the first Process
                      */
                     isFind = true;
                     if(cur == head){
                         curProcessPtr->setPtr(cur);
                         head = curProcessPtr;
                     }
                     else{
                         pre->setPtr(curProcessPtr);
                         curProcessPtr->setPtr(cur);
                     }
                     break;
                 }
                 pre = cur;
             }
             /*
              * refProcess'PriorityNum is the Minest
              */
             if(isFind == false){
                 tail->setPtr(curProcessPtr);
                 curProcessPtr->setPtr(NULL);
                 tail = curProcessPtr;
             }
         }
    }
    void DeleteProcess(){
        /*
         * We always Delete Process From Head of the List
         */
        if(Algo == Algo_RoundRobin){
            /*
             * ProcessDone
             */
            ProcessControlBlock* DeleteProcessPtr = head;
            if(head->getCurrentTime() >=head->getAllTime()){

                head = head->getPtr();
                DeleteProcessPtr->setPtr(NULL);
                DeleteProcessPtr->setState(State_Finish);
                /*
                * When it is the first ProcessDone
                */

                if(Ftail == NULL){
                    tail->setPtr(DeleteProcessPtr);
                    Ftail = DeleteProcessPtr;
                    DeleteProcessPtr->setPtr(NULL);
                }
                else{
                    Ftail->setPtr(DeleteProcessPtr);
                    Ftail = DeleteProcessPtr;
                    DeleteProcessPtr->setPtr(NULL);
                }

                return;
            }
            else{
                head = head->getPtr();
                DeleteProcessPtr->setPtr(tail->getPtr());
                DeleteProcessPtr->setState(State_Waiting);
                tail->setPtr(DeleteProcessPtr);
                tail = DeleteProcessPtr;
            }
        }
        else if(Algo == Algo_Priority){
            ProcessControlBlock* DeleteProcessPtr = head;
            /*
             * Hook
             */
            if(head->getCurrentTime() >=head->getAllTime()){

                head = head->getPtr();
                DeleteProcessPtr->setPtr(NULL);
                DeleteProcessPtr->setState(State_Finish);
                /*
                * When it is the first ProcessDone
                */

                if(Ftail == NULL){
                    tail->setPtr(DeleteProcessPtr);
                    Ftail = DeleteProcessPtr;
                    DeleteProcessPtr->setPtr(NULL);
                }
                else{
                    Ftail->setPtr(DeleteProcessPtr);
                    Ftail = DeleteProcessPtr;
                    DeleteProcessPtr->setPtr(NULL);
                }

                return;
            }
            else{

                head = head->getPtr();
                DeleteProcessPtr->setPtr(NULL);
                DeleteProcessPtr->setState(State_Waiting);
                ProcessControlBlock* pre = NULL;
                bool IsFind = false;

                for(ProcessControlBlock* cur = head ;cur!=NULL&&cur->getState()==State_Waiting;cur = cur->getPtr()){

                    if(DeleteProcessPtr->getPriorityNum() > cur->getPriorityNum()){
                        IsFind = true;
                        if(cur == head){
                            head = DeleteProcessPtr;
                            DeleteProcessPtr->setPtr(cur);
                        }
                        else{
                            pre->setPtr(DeleteProcessPtr);
                            DeleteProcessPtr->setPtr(cur);
                        }
                        break;
                    }
                    pre = cur;
                }

                if(IsFind == false){
                    DeleteProcessPtr->setPtr(tail->getPtr());
                    tail->setPtr(DeleteProcessPtr);
                    tail = DeleteProcessPtr;
                }

            }
        }
    }

    void RunOnce(){
        if(Algo == Algo_RoundRobin){
            head->setCurrentTime( head->getCurrentTime()+1);

            DeleteProcess();
        }
        else if(Algo == Algo_Priority){
            head->setCurrentTime(head->getCurrentTime()+1);
            head->setPriorityNum(head->getPriorityNum()-3);
            DeleteProcess();
        }
    }
    void PrintList(){

        X Node[ListLength];
        int Index = 0;
        for(ProcessControlBlock* cur = head ;cur!=NULL ; cur=  cur->getPtr()){

            Node[Index].ID = cur->getProcessIndex();
            Node[Index].PRIORITY = cur->getPriorityNum();
            Node[Index].CURTIME = cur->getCurrentTime();
            Node[Index].ALLTIME = cur->getAllTime();
            Node[Index].STATE = cur->getState();
            Node[Index].NEXT = (cur->getPtr()==NULL?0:cur->getPtr()->getProcessIndex());
            Index++;
        }

        cout<<"============================================"<<endl;
        cout<<"RUNNING PROC        WAITING QUEUE"<<endl;
        int t = 0;
        if(Node[0].STATE == State_Waiting) {
            printf("%d", Node[0].ID);
            t++;
        }
        cout<<"                  ";
        for(int i =t;i<ListLength;i++){
            if(Node[i].STATE == State_Finish)
                continue;
            printf("  %d",Node[i].ID);
        }
        cout<<endl;
        cout<<"============================================"<<endl;
        sort(Node,Node+ListLength,cmp);

        cout<<"ID                ";
        for(int i = 0;i<ListLength;i++){
            printf("%3d",Node[i].ID);
        }
        cout<<endl<<"Priority          ";
        for(int i = 0;i<ListLength;i++){
            printf("%3d",Node[i].PRIORITY);
        }
        cout<<endl<<"CURTIME            ";
        for(int  i =0;i<ListLength;i++){
            printf("%3d",Node[i].CURTIME);
        }
        cout<<endl<<"ALLTIME            ";
        for(int  i =0;i<ListLength;i++){
            printf("%3d",Node[i].ALLTIME);
        }
        cout<<endl<<"STATE             ";
        for(int i = 0;i<ListLength;i++){
            if(Node[i].STATE == State_Waiting){
                printf("  W");
            }
            else if(Node[i].STATE == State_Finish){
                printf("  F");
            }
            else if(Node[i].STATE == State_Running){
                printf("  R");
            }
        }
        cout<<endl<<"NEXT             ";
        for(int i = 0;i<ListLength;i++){
            printf("%3d",Node[i].NEXT);
        }
        cout<<endl<<endl<<endl;


    }
};

#endif //OSEX1_PROCESSLIST_H
