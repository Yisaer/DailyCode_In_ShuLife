#include <iostream>
using namespace std;

struct  Node{
    int data;
    Node* Next;
    Node(int data){
        this->data = data;
        Next = NULL;
    }
};

struct List{
    Node* head;
    List(){
        this->head = NULL;
    }
    void Append(Node* cur){
        cur->Next = head;
        head = cur;
    }
    void TransPring(){
        for(Node* cur =head ;cur!=NULL ; cur = cur->Next){
            cout<<cur->data<<" ";
        }
        cout<<endl;
    }
};


int main() {
    List* l1 = new List();
    List* l2 = new List();
    l1->Append(new Node(3));
    l1->Append(new Node(1));
    l2->Append(new Node(4));
    l2->Append(new Node(2));
    l1->TransPring();
    l2->TransPring();
    List * l3 = new List();

    Node* p1 = l1->head;
    Node* p2 = l2->head;
    while(p1!=NULL && p2 !=NULL){
        if(p1->data > p2->data){
            Node* tmp  = p2;
            p2 = p2->Next;
            l3->Append(tmp);
        }
        else if(p2->data >= p1->data){
            Node* tmp = p1;
            p1 = p1->Next;
            l3->Append(tmp);
        }
    }
    Node* cur = NULL;
    if(p1==NULL && p2!=NULL){
        for( cur = p2 ; cur->Next!=NULL;cur = cur->Next);
        cur->Next = l3->head;
        l3->head = p2;
    }
    else if(p1!=NULL && p2 ==NULL){
        for( cur = p1 ; cur->Next!=NULL ; cur= cur->Next);
        cur ->Next = l3->head;
        l3->head = p1;
    }
    l3->TransPring();

    return 0;
}