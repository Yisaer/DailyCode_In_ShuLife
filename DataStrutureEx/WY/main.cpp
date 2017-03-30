#include <iostream>
#include <cstring>
#include <cmath>
#include <string>
#include <vector>
using namespace std;
int const Maxn = 55;
int F[Maxn];
int T[Maxn];

int main (){

    long long k  = 0;
    int n = 0;
    while(cin>>n>>k){
        memset(T,0,sizeof(T));
        memset(F,0,sizeof(F));
        //int y = k%n;
        for(int i = 0;i<n;i++){
            cin>>F[i];
        }
        for(long long  j = 0;j<k;j++){
            for(int i = 0;i<n;i++){
                T[i] = (F[i]+F[(i+1)%n])%100;
            }
            for(int i = 0;i<n;i++){
                F[i] = T[i];
            }
        }
        for(int  i =0;i<n;i++){
            if(i) cout<<" ";
            cout<<F[i];
        }
        cout<<endl;
    }
}