#include <iostream>
using namespace std;
#include <cstring>


int Res[15];
int n,m;
struct Process{
    int Had[15];
    int Max[15];
    int Need[15];
    bool IsEnd;
}Processes[15];

int curProcess;
int Req[15];
Process tmpProcesses[15];

bool IsEnd(){
    bool R = true;
    for(int i = 0;i<n;i++){
        if(Processes[i].IsEnd == false){
            R = false;
            break;
        }
    }

    return R;
}
bool IstmpEnd(){
    bool R = true;
    for(int i = 0;i<n;i++){
        if(tmpProcesses[i].IsEnd == false){
            R = false;
            break;
        }
    }

    return R;
}

bool Legal= false;
bool Banker(){
    Legal = true;
    for(int i = 0;i<m;i++){
        if(Req[i] > Processes[curProcess].Need[i]){
            Legal = false;
            break;
        }
    }
    for(int i = 0;i<m;i++){
        if(Req[i] > Res[i]){
            Legal = false;
            break;
        }
    }
    int tmp[15];
    for(int i = 0;i<m;i++){
        tmp[i] = Res[i];
    }

    for(int i = 0;i<n;i++){
        for(int j = 0;j<m;j++){
            tmpProcesses[i].Max[j] = Processes[i].Max[j];
            tmpProcesses[i].Need[j] = Processes[i].Need[j];
            tmpProcesses[i].Had[j] = Processes[i].Had[j];
            tmpProcesses[i].IsEnd = Processes[i].IsEnd;
        }
    }

    for(int i = 0;i<m;i++){
        tmpProcesses[curProcess].Had[i] +=Req[i];
        tmpProcesses[curProcess].Need[i] -= Req[i];
        tmp[i]-=Req[i];
    }
    bool IsFind = false;
    do{
        IsFind = false;
        int curRel = -1;
        for(int i = 0;i<n;i++){
            if(tmpProcesses[i].IsEnd == false){
                bool IsFF = true;
                for(int j= 0;j<m;j++){
                    if(tmpProcesses[i].Need[j] > tmp[i]){
                        IsFF = false;
                        break;
                    }
                }
                IsFind = IsFF;
                if(IsFind == true){
                    curRel = i;
                    break;
                }
            }
        }
        if(IsFind){
            for(int i = 0;i<m;i++){
                tmp[i]+=tmpProcesses[curRel].Had[i];
                tmpProcesses[curRel].IsEnd = true;
                tmpProcesses[curRel].Had[i] = 0;
                tmpProcesses[curRel].Need[i] = 0;
            }
        }
    }while(!IstmpEnd() && IsFind == true);
    for(int  i = 0;i<m;i++){
        if(tmpProcesses[i].IsEnd == false){
            Legal = false;
            break;
        }
    }

    return Legal;
}


int main() {

    memset(Processes,0,sizeof(Processes));
    memset(Res,0,sizeof(Res));
    memset(Req,0,sizeof(Req));
    cin>>n>>m;
    for(int i = 0;i<m;i++){
        cin>>Res[i];
    }

    for(int i = 0;i<n;i++){
        for(int j = 0;j<m;j++){
            cin>>Processes[i].Had[j]>>Processes[i].Max[j];
        }
    }

    int option = -1;
    cin>>option;
    if(option == 0){
        do{
            for(int i = 0;i<n;i++){
                for(int j = 0;j<m;j++){
                    Processes[i].Need[j] = Processes[i].Max[j] - Processes[i].Had[j];
                }
            }
            cin>>curProcess;
            for(int i = 0;i<m;i++){
                cin>>Req[i];
            }
            Legal = false;
            Banker();
            if(Legal){
                Processes[curProcess].IsEnd = true;
                for(int i = 0;i<m;i++){
                    Processes[curProcess].Had[i]+=Req[i];
                    Processes[curProcess].Need[i]-=Req[i];
                    if(Processes[curProcess].Had[i] < Processes[curProcess].Max[i]){
                        Processes[curProcess].IsEnd = false;
                    }
                }
            }
        }while(!IsEnd());
    }
    return 0;
}