#include <iostream>
#include <cstdlib>
#include <fstream>
#include <string>
using namespace std;
#define FILENAME "pl0.txt"
#include <map>
#include <set>
#include <vector>
#include "help.h"


int main() {

    ifstream in(FILENAME);
    string line;
    set<string> Set;
    map<string,int> Map;
    map<string,string> MapV;
    map<string,int>::iterator it;
    string Const = "Const";
    string Var = "Var";


    if(in)
    {
        while (getline (in, line))
        {
            vector<string> d;
            string s = "";
            for(int i = 0;i<line.size();i++){
                s+=line[i];
                if(line[i] == ';'){
                    d.push_back(s);
                    s="";
                }
            }
            for(int i = 0;i<d.size();i++){
                string v1 = substr(0,3,d[i]);
                string v2 = substr(0,5,d[i]);
                if((v1 == Var)||v2 == Const){
                    int start = 0;
                    if(v1 == Var){
                        start = 4;
                    }else{
                        start = 6;
                    }
                    string word = "";
                    bool find = false;
                    for(int j = start ;j<d[i].size();j++){
                        if(d[i][j] == '=' ||d[i][j] == ';'){
                            break;
                        }
                        if(find == false && islegal(d[i][j])) {
                            if(d[i][j] >='0'&&d[i][j]<='9'){
                                continue;
                            }
                            if(d[i][j-1] !=' '&& d[i][j-1]!=','){
                                continue;
                            }
                            find = true;
                            word += d[i][j];

                        }
                        else if(find== true && !islegal(d[i][j])){
                            string tword = lower(word);
                            MapV[tword] = word;
                            Set.insert(tword);
//                            cout<<"word = "<<word<<endl;
                            Map[word]= 0;
                            word ="";
                            find = false;
                            if(d[i][j] == '='||d[i][j] == ';'){
                                break;
                            }
                        }
                        else if(find == true && islegal(d[i][j])){
                            find = true;
                            word +=d[i][j];
                        }
                    }
                }
            }
        }
    }
    else // 没有该文件
    {
        cout <<"no such file" << endl;
    }
//    for(it = Map.begin();it!=Map.end();it++){
//        cout<<it->first<<","<<it->second<<endl;
//    }
//    cout<<"+==================================="<<endl;
    ifstream In(FILENAME);
    if(In){
        while(getline(In,line)){
            vector<string> d;
            string s = "";
            for(int i = 0;i<line.size();i++){
                s+=line[i];
                if(line[i] == ';'){
                    d.push_back(s);
                    s="";
                }
            }
            for(int i = 0;i<d.size();i++){
                string s="";
                bool find =false;
                for(int j = 0;j<d[i].size();j++){
                    if(find == false && islegal(d[i][j])){
                        find = true;
                        s+=d[i][j];
                    }
                    else if(find == true && islegal(d[i][j])){
                        s+=d[i][j];
                    }
                    else if(find == true && !islegal(d[i][j])) {
                        find = false;
                        string t = lower(s);
                        s = "";

                        if (Set.count(t)) {
                            Map[MapV[t]]++;
                        }
                    }
                }
            }
        }
    }

    for(it = Map.begin();it!=Map.end();it++){
        cout<<it->first<<","<<it->second<<endl;
    }
    return 0;
}