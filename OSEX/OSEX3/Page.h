//
// Created by 高松 on 2016/12/13.
//

#ifndef OSEX3_PAGE_H
#define OSEX3_PAGE_H


class Page{
public:
    bool IsInPageList ;
    int PageIndex;
    int PageLen;
    int PageAddressBegin;

    Page(int pageIndex , int pagelen,int pageaddressbegin){
        IsInPageList = false;
        this->PageIndex = pageIndex;
        this->PageLen = pagelen;
        this->PageAddressBegin = pageaddressbegin;
    }
};

#endif //OSEX3_PAGE_H
