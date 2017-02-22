#include <iostream>
using namespace std;
#include <cstring>
#include <queue>

struct Node{
    int data;
    Node* NextArr[2];
    Node(){
        memset(NextArr,0,sizeof(NextArr));
    }
};
struct List{
    Node* root;
};
bool Arr[10];
queue<Node*> q;

bool IsCompleteTree(List* list){
    bool HadNext = true;
    Node* root = list->root;
    q.push(root);
    bool IsLegal = true;
    while(!q.empty()){
        Node* cur  = q.front();
        q.pop();
        for(int i =0;i<2;i++){
            if(HadNext == true ){
                if(cur->NextArr[i] == NULL){
                    HadNext = false;
                }
                else{
                    q.push(cur->NextArr[i]);
                }
            }
            else{
                if(cur->NextArr[i] != NULL ){
                    IsLegal = false;
                }
            }
        }
    }
    return IsLegal;
}


void Trans(Node* cur,int curNum){
    cout<<"curNum = "<<curNum<<endl;
    for(int i=0;i<2;i++){
        if(cur->NextArr[i] != NULL ){
            Arr[curNum*2+i] = true;
            Trans(cur->NextArr[i],curNum*2+i);
        }
    }
}

bool IsCompleteTree2(List* list,int nodeNum){
    memset(Arr,0,sizeof(Arr));
    Node Solider;
    Solider.NextArr[1] = list->root;
    Trans(&Solider,0);
    for(int i = 1;i<=nodeNum;i++){
        if(Arr[i] != true){
            return false;
        }
    }
    return true;
}



int main() {
    while(!q.empty()){
        q.pop();
    }
    cout<<"here1"<<endl;

    List list;
    Node root;
    Node a;
    Node b;
    Node c;
    Node d;
    root.NextArr[0]  = &a;
    root.NextArr[1] = &b;
    a.NextArr[1] = &d;
    b.NextArr[0] = &c;
    list.root = &root;

    bool IS = IsCompleteTree(&list);
    bool IS2 = IsCompleteTree2(&list,5);
    cout<<"here"<<endl;
    if(IS2){
        cout<<"yes"<<endl;
    }
    else{
        cout<<"no"<<endl;
    }



    return 0;
}