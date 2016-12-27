#include <iostream>
using namespace std;
#include <limits>

int Arr[20][20];
int ColMin[20];
int RowMax[20];
int Map[20];

int main() {
    int n;
    int m;
    cin>>n>>m;
    memset(Arr,0,sizeof(Arr));
    memset(ColMin,0,sizeof(ColMin));
    memset(RowMax,0,sizeof(RowMax));
    memset(Map,-1,sizeof(Map));

    for(int i = 0;i<n;i++){
        for(int j = 0;j<m;j++){
            cin>>Arr[i][j];
        }
    }
    for(int i = 0;i<n;i++){
        int32_t RowMaxVal = INT32_MIN;
        for(int j = 0;j<m;j++){
            if(Arr[i][j] > RowMaxVal){
                RowMaxVal = Arr[i][j];
                RowMax[i] = j;
            }
        }
    }

    for(int j = 0;j<m;j++){
        int32_t ColMinVal = INT32_MAX;

        for(int i = 0;i<n;i++){
            if(Arr[i][j] < ColMinVal ){
                ColMinVal =  Arr[i][j];
                ColMin[j] = i;

            }
        }
        Map[ColMin[j]] = j;
    }



    for(int i = 0;i<n;i++){
        if( ColMin[RowMax[i]] == i){
            cout<<i<<","<<RowMax[i]<<endl;
        }
    }

    return 0;
}