//
// Created by 高松 on 2016/12/13.
//

#ifndef OSEX3_PAGESERVICE_H
#define OSEX3_PAGESERVICE_H

#include"PageList.h"
#include <string>

class PageService{
public:
    int Operno[256];
    PageList* pagelistptr;
    PageService(){
        int len = 0;
        cin>>len;
        for(int i = 0;i<=255;i++){
            cin>>Operno[i];
            pagelistptr = new PageList(len,Operno,256);
        }
        string a;
        cin>>a;
        if(a == "opt"){
            pagelistptr->Opt();
        }
        else if(a == "lru"){
            pagelistptr->Lru();
        }
    }
};



#endif //OSEX3_PAGESERVICE_H
