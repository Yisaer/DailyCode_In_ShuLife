#include "PolyNoimalService.h"

int main (){
    PolyNoimalService* polyNoimalService;
    polyNoimalService = new PolyNoimalService();
    cout<<"Add ALL TO One"<<endl;
  polyNoimalService->PrintOneList(polyNoimalService->addAllToOne());
    cout<<endl<<"Muti ALL TO ONE"<<endl;
    polyNoimalService->PrintOneList(polyNoimalService->MultiAllToOne());


//
//    PolyNoimalList* polyNoimalList2 =NULL;
//    PolyNoimalList* polyNoimalList1 =NULL;
//    PolyNoimalList* polyNoimalList3 = NULL;
//    polyNoimalList2 = new PolyNoimalList();
//    polyNoimalList2->addNoimal(new Noimal(1,4));
//    polyNoimalList2->addNoimal(new Noimal(2,1));
//    polyNoimalList1 = new PolyNoimalList();
//    polyNoimalList3 = new PolyNoimalList();
//    polyNoimalList1->addNoimal(new Noimal(3,4));
//    polyNoimalList1->addNoimal(new Noimal(1,2));
//    polyNoimalList3->addNoimal(new Noimal(5,6));
//    polyNoimalList3->addNoimal(new Noimal(7,8));
//    vector<Noimal*> cur;
//    PolyNoimalListUitl* polyNoimalListUitl =NULL;
//    cur.push_back(polyNoimalList1->getHead());
//    cur.push_back(polyNoimalList3->getHead());
//    cur.push_back(polyNoimalList2->getHead());
//    polyNoimalListUitl = new PolyNoimalListUitl(cur);
//    PolyNoimalList* reslist2 = polyNoimalListUitl->AddAllList();
//    reslist2->TransPrint();
//    PolyNoimalList* reslist = polyNoimalListUitl->MutiAllList();
//    reslist->TransPrint();


//    polyNoimalList3->addNoimal(new Noimal(8,9));
//    polyNoimalList1->addNoimal(new Noimal(9,10));


//    polyNoimalList2->addNoimal(new Noimal(3,7));
//
//    polyNoimalList1->TransPrint();
//    cout<<endl;
//    polyNoimalList2->TransPrint();
//    cout<<endl;
//
//    PolyNoimalListUitl* polyNoimalListUitl = NULL;
//    vector<Noimal*> arr;
//    arr.push_back(polyNoimalList1->getHead());
//    arr.push_back(polyNoimalList2->getHead());
//    arr.push_back(polyNoimalList3->getHead());
//    polyNoimalListUitl = new PolyNoimalListUitl(arr);
//    PolyNoimalList* resList =NULL;
//  resList = polyNoimalListUitl->AddTwolist(polyNoimalList1->getHead(),polyNoimalList2->getHead());
//    resList = polyNoimalListUitl->MultiTwoList(polyNoimalList1->getHead(),polyNoimalList2->getHead());
//    resList = polyNoimalListUitl->AddAllList();
//    resList = polyNoimalListUitl->MutiAllList();
//
//    resList->TransPrint();


}