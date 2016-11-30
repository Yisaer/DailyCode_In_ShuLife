//
// Created by Yisa on 2016/11/29.
//

#ifndef OSEX1_PROCESSCONTROBLOCK_H
#define OSEX1_PROCESSCONTROBLOCK_H
#include <iostream>
using namespace std;
#include <cstdlib>

int static State_Waiting = 0;
int static State_Running = 1;
int static State_Finish = 2;



class ProcessControlBlock{
private:
    int static CurrentProcessCount ;
    int ProcessIndex;
    ProcessControlBlock* ptr;
    int PriorityNum;
    int CurrentTime;
    int State;
    int Alltime;
public:
    ProcessControlBlock(){
        ProcessIndex = ++CurrentProcessCount;
        ptr = NULL;
        PriorityNum =  rand()%5+1;
        CurrentTime = 0;
        State = State_Waiting;
        Alltime = PriorityNum;
    }

    int getAllTime(){
        return Alltime;
    }
    int getProcessIndex(){
        return ProcessIndex;
    }
    ProcessControlBlock* getPtr (){
        return ptr;
    }
    int getPriorityNum(){
        return PriorityNum;
    }
    int getCurrentTime(){
        return CurrentTime;
    }
    int getState (){
        return State;
    }
    void setPtr(ProcessControlBlock* ptr){
        this->ptr = ptr;
    }
    void setCurrentTime(int currentTime){
        this->CurrentTime = currentTime;
    }
    void setState(int State){
        this->State = State;
    }
    void setPriorityNum(int PriorityNum){
        this->PriorityNum = PriorityNum;

    }
};
int ProcessControlBlock::CurrentProcessCount= 0 ;
#endif //OSEX1_PROCESSCONTROBLOCK_H
