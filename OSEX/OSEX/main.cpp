#include "ProcessDispatch.h"
#include <map>

map<string,int> m;
string static string_Algo_RoundRobin = "roundrobin";
string static string_Algo_Priority = "priority";

int main() {

    m[string_Algo_Priority] = Algo_Priority;
    m[string_Algo_RoundRobin] = Algo_RoundRobin;
    cout<<"TYPE THE ALGORITHM:  "<<endl;
    string str;
    cin>>str;
    for(int i = 0;i<str.length();i++){
        if(str[i]>='A'&&str[i]<='Z'){
            str[i] = str[i]-'A'+'a';
        }
    }
    ProcessDispatch* processDispatch = NULL;
    processDispatch = new ProcessDispatch(5,m[str]);
    processDispatch->Running();

    return 0;
}