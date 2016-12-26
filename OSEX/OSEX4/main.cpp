#include <iostream>
using namespace std;
#include <string>


struct User{
    string user;
    int FileNode;
}Med[12];

struct File {
    int DPL;
    string content;
    string fileName;
};

struct UserFile{
    int fileCount ;
    File file[12];

}AFD[12];


string Iniopr[7] = { "delete","creat","open","close","write","read","bye"};
bool DeleteFile(int FileIndex,int User){
    if(!(AFD[User].file[FileIndex].DPL/4)  ){
        return false;
    }
    for(int i = FileIndex;i<AFD[User].fileCount-1;i++){
        AFD[User].file[i] = AFD[User].file[i+1];
    }
    AFD[User].fileCount--;
    return true;
}
bool Creat(int User){
    if(AFD[User].fileCount == 10){
        return false;
    }
    cin>>AFD[User].file[AFD[User].fileCount].fileName;
    cin>>AFD[User].file[AFD[User].fileCount].content;
    AFD[User].file[AFD[User].fileCount].DPL = 7;
    AFD[User].fileCount++;
    return true;
}


int main() {

    string usr;
    cin>>usr;
    int curUser = -1;
    for(int  i =0;i<10;i++){
        if(usr == Med[i].user){
            curUser = i;
            break;
        }
    }
    if( curUser  == -1){
        return 0 ;
    }
    for(int  i =0;i<AFD[curUser].fileCount;i++){
        cout<<AFD[curUser].file[i].fileName<<endl;
    }
    string opr;
    while(true) {
        int curOper;
        cin >> opr;
        for (int i = 0; i < 6; i++) {
            if (opr == Iniopr[i]) {
                curOper = i;
                break;
            }
        }
        int FileIndex;
        switch (curOper) {
            case 0:
                cin >> FileIndex;
                DeleteFile(FileIndex, curUser);
                break;
            case 1:
                Creat(curUser);
                break;
            case 6:
                goto A;
            default:
                continue;
        }
    }
    A:
    for(int i = 0;i<AFD[curUser].fileCount;i++){
        cout<<AFD[curUser].file[i].fileName<<endl;
    }
    cout<<"Good bye"<<endl;


    return 0;
}