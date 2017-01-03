#include <iostream>
using namespace std;

#include "Node.h"
#include <stack>


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
    stack <Node*> s;
    Node* cur = root;
    while( cur !=NULL||!s.empty() ){
        while(cur!=NULL){
            s.push(cur);
            cur = cur->lson;
        }
        if(!s.empty()){
            cur = s.top();
            cout<<cur->data<<endl;
            s.pop();
            cur = cur->rson;
        }
    }

    return 0;
}

