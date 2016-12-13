//
// Created by 高松 on 2016/12/13.
//

#ifndef OSEX3_PAGELIST_H
#define OSEX3_PAGELIST_H
#include <cstring>
#include "Page.h"
#include <iostream>
using namespace std;


class PageList{
public:
    int PageTablelen;
    Page* PageTable[32];
    int * OperAddress;
    int OperCount;
    PageList(int len , int* OperAddress,int operCount){
        this->PageTablelen = len;
        this->OperAddress = OperAddress;
        this->OperCount = OperCount;
    }
    void Opt(){
        for(int t = 1;t<=8;t*=2) {
            int Appear[33];
            memset(Appear,0,sizeof(Appear));
            for(int i = 0;i<OperCount;i++){
                int Index = OperAddress[i]/(t*1024)+1;
                cout<<"Page["<<i<<"]="<<Index<<endl;
                Appear[Index]++;
            }
            double Poiss[33];
            memset(Poiss,0,sizeof(Poiss));
            for(int i = 1;i<=32;i++){
                Poiss[i] = Appear[i]*1.0 / OperCount;
            }
        }
    }

    void Lru(){
        for(int t = 1;t<=8;t*=2){
            int PageT[PageTablelen];
            memset(PageT,0,sizeof(PageT));
            for(int i = 1;i<=32/i;i++){
                PageTable[i] = new Page(i,t*1024,(i-1)*1024*t);
            }
            for(int i = 0;i<OperCount;i++){
                int Index = OperAddress[i]/(i*1024)+1;
                cout<<"Pageno["<<i<<"]="<<Index<<endl;
                PageTable[Index] ->IsInPageList = true;
                for(int i=2;i<32;i++){
                    PageT[i] = PageT[i-1];
                }
                PageT[1] = Index;
            }
        }
    }

};

#endif //OSEX3_PAGELIST_H
