#include <iostream>
#include "List.h"
#include "Seq.h"
void TransList(List* list){
    Node* tmp = list->getHead();
    list->setHead(list->gettail());
    list->setTail(tmp);
    int i = list->geti();
    if(i == 1){
        list->seti(0);
    }
    else{
        list->seti(1);
    }
}

void TransSeq(Seq* seq){
    int tmp = seq->s;
    seq->step = - seq->step;
    seq->s = seq->e;
    seq->e = tmp;

}
int main() {

    List * list = NULL;
    list = new List();
    list->append(new Node(1));
    list->append(new Node(2));
    list->append(new Node(3));
    list->Print();
    TransList(list);
    list->Print();
    TransList(list);
    list->Print();
    cout<<"Arr"<<endl;
    Seq* seq = NULL;
    seq = new Seq();
    seq->Append(new Node(1));
    seq->Append(new Node(2));
    seq->Append(new Node(3));
    seq->Print();
    TransSeq(seq);
    seq->Print();
    TransSeq(seq);
    seq->Print();
}


