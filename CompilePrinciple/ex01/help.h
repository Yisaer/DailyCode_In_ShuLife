//
// Created by 高松 on 2017/3/30.
//

#ifndef EX01_HELP_H
#define EX01_HELP_H


string lower(string s){
    string re = "";
    for(int i  =0;i<s.size();i++){
        if(s[i]>='A'&&s[i]<='Z'){
            s[i] = s[i]-'A'+'a';
            re+=s[i];
        }else{
            re+=s[i];
        }
    }
    return re;
}

string substr(int s,int len,string z){
    string re="";
    for(int i = s;i<s+len;i++){
        re +=z[i];
    }
    return re;
}

bool islegal(char c){
    if ( c >='a'&&c<='z'){
        return true;
    }
    if( c>='0'&&c<='9'){
        return true;
    }
    if(c>='A'&&c<='Z'){
        return true;
    }
    return false;
}
#endif //EX01_HELP_H
