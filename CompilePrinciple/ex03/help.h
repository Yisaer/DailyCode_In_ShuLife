//
// Created by 高松 on 2017/4/6.
//

#ifndef EX02_HELP_H
#define EX02_HELP_H

bool IsAlpha(char ch){
    if((ch >='a'&&ch<='z')||(ch>='A'&&ch<='Z')){
        return true;
    }
    return false;
}

bool IsDigit(char ch){
    if( ch>='0'&&ch<='9'){
        return true;
    }
    return false;
}

bool IsAlphOrNum(char ch){
    if(IsAlpha(ch)){
        return true;
    }
    if(IsDigit(ch)){
        return true;
    }
    return false;
}
#endif //EX02_HELP_H
