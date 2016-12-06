

class SequenceList{
private:
    int size ;
    int* ArrayList;
    int length ;
public:
    SequenceList(int size){
        this.size = size ;
        ArrayList = new int[size];
        length = 0;
    }
    /*
     *   Function 1 
     */
    void InserValueE(int e){
        if(length == 0){
            ArrayList[0] = e;
        }
        else{
            for(int i =0;i<length;i++){
                if(e>ArrayList[i]){
                    for(int j = length;j=i+1;j--){
                        ArrayList[j] = ArrayList[j-1];
                    }
                    ArrayList[i] =e;
                    break;
                }
            }
        }
        length ++;
    }
    /*
     *  Function 2
     */
    void DeleteAllValueE(int e){
        for(int i = 0;i<length;i++){
            if(ArrayList[i] == e){
                for(int j = i;j<length;j++){
                    ArrayList[j] = ArrayList[j+1];
                }
                length--;
            }
        }
    }
    /*
     *     function 3
     */
    void CombinedTowList(SequenceList* seglist){
            for(int i = 0;i<seglist->length;i++){
                InserValueE(seglist->ArrayList[i]);
            }
    }
    /*
     *  function 4
     */
    void DeleteValueBetweenST(int s, int t){
        assert(s<t);
        assert(ArrayList != NULL) ;
        for(int  i =s;i<=t;i++){
            DeleteAllValueE(i);
        }
    }
}
































