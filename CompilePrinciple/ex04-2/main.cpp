#include <iostream>
using namespace std;
#include <cstdio>
#include <cstring>
#include <stack>
#define LL long long
using namespace std;
#define maxn 5005
char s[maxn];
stack<char> s1;

LL num[maxn];
char sig[maxn];
int nn,sn;
char Tnum[maxn];
char ssig[maxn];

int pri(char c){
    if(c=='+'||c=='-') return 1;
    if(c=='*'||c=='/') return 2;
    if(c=='(') return 0;
}

char Precede(char a, char b){
    int i,j;
    char pre[][7]={
            /*运算符之间的优先级制作成一张表格*/
            {'>','>','<','<','<','>','>'},
            {'>','>','<','<','<','>','>'},
            {'>','>','>','>','<','>','>'},
            {'>','>','>','>','<','>','>'},
            {'<','<','<','<','<','=','0'},
            {'>','>','>','>','0','>','>'},
            {'<','<','<','<','<','0','='}};
    switch(a){
        case '+': i=0; break;
        case '-': i=1; break;
        case '*': i=2; break;
        case '/': i=3; break;
        case '(': i=4; break;
        case ')': i=5; break;
        case '#': i=6; break;
    }
    switch(b){
        case '+': j=0; break;
        case '-': j=1; break;
        case '*': j=2; break;
        case '/': j=3; break;
        case '(': j=4; break;
        case ')': j=5; break;
        case '#': j=6; break;
    }
    return pre[i][j];
}

void cal(){
    char isG = Precede(sig[sn],sig[sn+1]);
    if(isG== '>'){
        if(sig[sn]=='+') Tnum[nn-1]=num[nn]+num[nn-1];
        if(sig[sn]=='-') Tnum[nn-1]=num[nn-1]-num[nn];
        if(sig[sn]=='*') Tnum[nn-1]=num[nn-1]*num[nn];
        if(sig[sn]=='/') Tnum[nn-1]=num[nn-1]/num[nn];
    }
    else if(isG=='<'){
        if(sig[sn]=='+') Tnum[nn-1]=Tnum[nn]+num[nn-1];
        if(sig[sn]=='-') Tnum[nn-1]=Tnum[nn-1]-num[nn];
        if(sig[sn]=='*') Tnum[nn-1]=Tnum[nn-1]*num[nn];
        if(sig[sn]=='/') Tnum[nn-1]=Tnum[nn-1]/num[nn];
    }
    if(sig[sn]=='+') num[nn-1]=num[nn]+num[nn-1];
    if(sig[sn]=='-') num[nn-1]=num[nn-1]-num[nn];
    if(sig[sn]=='*') num[nn-1]=num[nn-1]*num[nn];
    if(sig[sn]=='/') num[nn-1]=num[nn-1]/num[nn];
    nn--;
    sn--;
}


LL solve(int l,int r){
    nn=sn=0;
    int curn=0;
    for(int i=l;i<=r;i++){
        if(isdigit(s[i])){
            curn=curn*10+s[i]-'0';
        }
        else {
            if(s[i]=='(') {
                sig[++sn]=s[i];
                continue;
            }
            if(s[i-1]!=')'){
                num[++nn]=curn;
                curn=0;
            }
            if(s[i]==')'){
                while(sig[sn]!='(') cal();
                sn--;
                continue;
            }
            if(sn&&pri(s[i])<=pri(sig[sn])) cal();
            sig[++sn]=s[i];
        }
    }
    if(s[r]!=')') num[++nn]=curn;
    while(nn>1) cal();

    return num[1];
}

int main(){
    while(~scanf("%s",s)){
        while(!s1.empty()){
            s1.pop();
        }
        bool isLegal = true;
        for(int  i =0 ;i<strlen(s);i++) {
//            printf("i = %d\n",i);
            if(!s1.empty()){
//                printf("c = %c \n",s1.top());
            }
            if (s[i] == '(') {
                s1.push(s[i]);
            }
            if(s[i] == ')'){
                if(s1.empty()){
                    isLegal = false;
                    break;
                }
                if(s1.top() == '('){
                    s1.pop();
                }
                else{
                    isLegal = false;
                    break;
                }

            }
        }
        if( isLegal == false || !s1.empty()){
            printf("error\n");
            continue;
        }

        cout<<solve(0,strlen(s)-1)<<endl;
    }
    return 0;
}