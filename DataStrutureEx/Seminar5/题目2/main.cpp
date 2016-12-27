#include <iostream>
using namespace std;
#include <string>
#include <cstring>
int Cnt[26];


int main() {
    memset(Cnt,0,sizeof(Cnt));
    string str;
    cin>>str;
    string::iterator it;
    for(it = str.begin();it!=str.end();it++){
        Cnt[*it-'a']++;
    }
    for(int i = 0;i<26;i++){
        if(Cnt[i] == 0) continue;
        cout<<"Count "<<char(int('a')+i)<<" = "<<Cnt[i]<<endl;
    }

    return 0;
}