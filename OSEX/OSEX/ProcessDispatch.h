//
// Created by Yisa on 2016/11/29.
//

#ifndef OSEX1_PROCESSDISPATCH_H
#define OSEX1_PROCESSDISPATCH_H

#include "ProcessList.h"

class ProcessDispatch{
private:
    int ListLength;
    ProcessList* processList = NULL ;
    int Algo;
public:
    ProcessDispatch(int length,int algo){
        this->ListLength = length;
        this->Algo = algo;
        processList = new ProcessList(algo);
        for(int i = 0;i<ListLength;i++){
            ProcessControlBlock* process = new ProcessControlBlock();
            processList->setListLength(processList->getListLengthn()+1);
            processList->addProcess(process);
        }
    }
    void Running(){
        while(processList->IsRunning()){
            processList->PrintList();
            processList->RunOnce();
        }
        processList->PrintList();
        cout<<"============================================"<<endl;
    }
};

#endif //OSEX1_PROCESSDISPATCH_H
