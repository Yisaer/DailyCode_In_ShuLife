#include <iostream>
using namespace std;
#include <string>




struct Node{
    Node* lson;
    Node* rson;
    string data;
    Node(string str){
        data = str;
        lson = rson = NULL;
    }
    void setlson(Node* lson){
        this->lson = lson;
    }
    void setrson(Node* rson){
        this->rson = rson;
    }
};

int getCount(Node* cur){
    if(cur == NULL){
        return 0 ;
    }
    else{
        return getCount(cur->lson)+getCount(cur->rson)+1;
    }
}

int main() {
    Node* root = new Node("root");
    Node* A = new Node("A");
    root->setlson(A);
    Node* B = new Node("B");
    root->setrson(B);
    Node* C = new Node("C");
    A->setlson(C);
    Node* D = new Node("D");
    A->setrson(D);
    Node* E = new Node("E");
    B->setlson(E);
    Node* F = new Node("F");
    B->setrson(F);
    int Count = getCount(root);
    cout<<Count<<endl;
    return 0;
}