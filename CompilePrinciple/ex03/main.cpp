#include <iostream>
using namespace std;
#include <cstring>
#include <cstdio>
#include <fstream>
#include <string>
#include <map>
#include "help.h"
#include "CIFA.h"
#define FILEPATH "test.txt"
#define TOKENPATH "Mapper.txt"
#define TABSIZE 10

char prog[80],token[10];
char ch;
int syn,p,m = 0,n,row,sum = 0;
char *rwtab[TABSIZE] = {
        "begin","if","then","while","do","end","read","write","const","var",
};

map<int ,string> Mapper;


void scaner(){
    for(n = 0;n<8;n++){
        token[n] = NULL;
    }
    ch = prog[p++];
    while(ch == ' '){
        ch = prog[p];
        p++;
    }
    if(IsAlpha(ch)){
        m = 0;
        while(IsAlphOrNum(ch)){
            token[m++] = ch;
            ch = prog[p++];
        }
        token[m++] = 0;
        p--;
        syn = 10;
        for(n = 0;n<TABSIZE;n++){
            if(strcmp(token,rwtab[n])==0){
                syn = n+31;
                break;
            }
        }
    }
    else if( IsDigit(ch)){
        sum  = 0;
        while(isdigit(ch)){
            sum = sum*10 +ch-'0';
            ch = prog[p++];
        }
        p--;
        syn = 11;
        if(sum>32767){
            syn = -1;
        }
    }else switch(ch){
            case '<': m = 0; token[m++] = ch;
                ch = prog[p++];
                if(ch == '='){
                    syn = 22;
                    token[m++] = ch;
                }
                else {
                    syn = 23;
                    p--;
                }
                break;
            case '>': m = 0; token[m++] = ch;
                ch = prog[p++];
                if( ch == '='){
                    syn = 24;
                    token[m++] = ch;
                }
                else{
                    syn = 20;
                    p--;
                }
                break;
            case ':': m = 0;token[m++] = ch;
                ch = prog[p++];
                if( ch =='='){
                    syn = 18;
                    token[m++] = ch;
                }
                else{
                    syn = 17;
                    p--;
                }
                break;
            case '#' : syn=21;token[0] = ch; break;
            case ',' : syn=19;token[0] = ch; break;
            case '*' : syn=13;token[0] = ch; break;
            case '/' : syn=14;token[0] = ch; break;
            case '+' : syn=15;token[0] = ch; break;
            case '-' : syn=16;token[0] = ch; break;
            case '=' : syn=25;token[0] = ch; break;
            case ';' : syn=26;token[0] = ch; break;
            case '(' : syn=27;token[0] = ch; break;
            case ')' : syn=28;token[0] = ch; break;
            case '.' : syn=0; token[0] = ch; break;
            default: syn = -1; break;
        }
}

int main() {
    Mapper.clear();
    p = 0;
    row = 1;
    ifstream MapFile(TOKENPATH);
    string line;
    if(MapFile){
        while(getline(MapFile,line)){
            bool IsNum = false;
            int sum = 0;
            string token = "";
            for(int  i =0;i<line.size();i++){
                if( line[i] ==' '){
                    IsNum = true;
                    continue;
                }
                if(IsNum == true ){
                    sum = sum*10+(char)line[i]-'0';
                }
                else {
                    token += line[i];
                }
            }
            Mapper[sum] = token;
        }
    }
    Mapper[0] = "peroid";
//    map<int,string>:: iterator it;
//    for(it = Mapper.begin();it!=Mapper.end();it++){
//        cout<<it->first<<","<<it->second<<endl;
//    }
//    return 0 ;

    ifstream in(FILEPATH);
    if(in){
        while(getline(in,line)){
            char c[100];
            strcpy(c,line.c_str());
//            cout<<c<<endl;
            for(int i = 0;i<strlen(c);i++){
                ch = line[i];
//                cout<<"here"<<endl;
                if( ch >= 'A' &&ch <='Z'){
                    ch = ch -'A'+'a';
                }
                prog[p++] = ch;
                if(ch == '.'){
                    goto A;
                }
            }
        }
    }


    A:
    p = 0;
    bool isFalse = false;
    do {
        scaner();
//        cout<<"syn = "<<syn<<endl;
        synNum[cnt] = syn;
        cnt++;
        switch(syn){
            case 11: cout<<"("<<Mapper[11]<<" "<<sum<<")"<<endl;break;
            case -1: cout<<" Error  "<<endl; isFalse = true;break;
            default: cout<<"("<<Mapper[syn]<<" "<<token<<")"<<endl;break;
        }
    }while(syn!=0 && prog[p] != 0);
    if(isFalse == true){
        return 0;
    }
    return 0;
}


