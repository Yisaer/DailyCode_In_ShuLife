//
// Created by 高松 on 2017/4/20.
//

#ifndef EX03_CIFA_H
#define EX03_CIFA_H
bool isCorrect = true;
int synNum[200];
int cnt=  0;

void Minus(){

}

void Plus(){

}

void identy(){

}

void unsign(){


}
void yinzi(){
    identy();
    unsign();
}

void factor(){
    yinzi();
}

void term(){
    factor();
}

void Expression(){
    Plus();
    Minus();
    term();
}


#endif //EX03_CIFA_H
