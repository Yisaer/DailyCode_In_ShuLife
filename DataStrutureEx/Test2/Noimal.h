//
// Created by 高松 on 2016/11/30.
//

#ifndef TEST2_NOIMAL_H
#define TEST2_NOIMAL_H
#include <iostream>
using namespace std;

class Noimal{
private:
    int coef;
    int expo;
    Noimal * ptr ;
public:
    Noimal(){
        this->coef = 0;
        this->expo = 0;
        ptr = NULL;
    }
    Noimal(int coef , int expo){
        this->coef = coef;
        this->expo = expo;
        ptr = NULL;
    }
    int getCoef(){
        return coef;
    }
    int getExpo(){
        return expo;
    }
    void setCoef(int coef){
        this->coef = coef;
    }
    void setExpo(int expo){
        this->expo;
    }
    Noimal* getPtr(){
        return ptr;
    }
    void setPtr(Noimal * ptr){
        this->ptr = ptr;
    }
    void DeleteSelf(){
        delete this;
    }
    void add(Noimal* refNoimal){
        this->coef+=refNoimal->coef;
    }



};



#endif //TEST2_NOIMAL_H
