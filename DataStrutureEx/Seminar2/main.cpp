#include <iostream>
using namespace std;
#include <stack>

int static Multi = 5;
int static Divide = 15;
int static Minus = 1;
int static Plus = 11;


struct Node{
    int f ;
    int val;
    Node* Next ;
    Node(int f,int val){
        Next = NULL;
        this->f = f;
        this->val = val;
    }
};
int main() {

    stack<Node*> sVal;
    Node* head = NULL;
    Node* pre = NULL;

    for(int i = 0;i<5;i++){
        int f,val;
        cin>>f>>val;
        if(i == 0){
            head = new Node(f,val);
            pre = head;
        }
        else{
            pre->Next = new Node(f,val);
            pre = pre->Next;
        }
    }

    for(Node* cur = head ;cur!=NULL ; cur = cur->Next ){
        if(cur->f == -1 ){
           sVal.push(cur);
        }
        else{
            int x1 = sVal.top()->val;
            sVal.pop();
            int x2 = sVal.top()->val;
            sVal.pop();
            if(cur->f == Multi){
                x1 *= x2;
            }
            else if(cur->f == Minus){
                x1 -=x2;
            }
            else if(cur->f == Divide){
                x1/=x2;
            }
            else if(cur->f == Plus){
                x1+=x2;
            }
            sVal.push(new Node(-1,x1));
        }
    }
    int ans = sVal.top()->val;
    cout<<ans<<endl;
    return 0;
}