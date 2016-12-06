#include <iostream>
using namespace std;

int const Maxn = 1e5+5;
int F[Maxn];
int T[Maxn];

int main() {
    int n;
    cin>>n;
    for(int i = 0;i<n;i++){
       cin>>F[i];
    }
    int count = 0;
    for(int i = 0;i<n;i++){
        if(count == 0){
            T[count] =F[i];
            count++;
        }
        else{
            if(F[i] != T[count-1]){
                T[count] = F[i];
                count++;
            }
        }
    }
    for(int i = 0;i<count;i++){
        F[i] = T[i];
    }

    n = count;
    for(int  i =0;i<n;i++){
        if(i)cout<<" ";
        cout<<F[i];
    }   cout<<endl;


    return 0;
}