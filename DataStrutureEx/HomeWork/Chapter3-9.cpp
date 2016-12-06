
class Node{
private:
    int data;
    int freq;
    Node* prior
    Node* Next;
public:
    Node(int data){
        this.data = data;
        this.freq = 0;
        prior = NULL;
        Next = NULL;
    }
}

class List{
private:
    Node* head;
public:
    /*
     *  Function Locate
     */
     void LocateX(int x){
        for( Node * cur = head ; cur!=NULL ; cur = cur->Next){
            if(cur ->data == x){
                cur ->freq ++;
            }
        }
        ReArrange();
     }
     /*
      * Function  ReArrange the List
      */
      void ReArrange(){
           for(Node* cur =  head->Next ; cur != NULL ;cur = cur->Next){
                if( cur ->freq > cur->prior->freq){
                    Node* curNext = cur ->Next;
                    for(Node* tmp = cur -> prior ; tmp !=NULL ; tmp= tmp ->prior){
                        if( tmp -> freq = cur->freq){
                            Node* tmpNext =  tmp->Next;
                            Node* curPrev =  cur->prior;
                            tmp->Next = cur;
                            cur->prior =tmp;
                            tmpNext ->prior = cur;
                            tmpNext->Next = curNext;
                            if(curNext!=NULL){
                                cur->prior = tmpNext;
                            }
                        }
                    }   
                    cur = curNext;  
                }
            }
    }
}


