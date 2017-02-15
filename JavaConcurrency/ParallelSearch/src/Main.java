import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static ExecutorService pool = Executors.newCachedThreadPool();
    static final int Thread_Num = 2;
    static AtomicInteger res = new AtomicInteger(-1);
    static int[] arr;

    public static int search(int searchValue,int beginPos,int endPos){

        for(int i = beginPos;i<endPos;i++){
            if(res.get()>=0){
                return res.get();
            }
            if(arr[i] == searchValue){
                System.out.println("Found");
                if( !res.compareAndSet(-1,i)){
                    return res.get();
                }
                return i;
            }
        }
        return -1;
    }

    public static class SearchTask implements Callable<Integer>{

        int begin,end,searchValue;

        public SearchTask(int begin, int end, int searchValue) {
            this.begin = begin;
            this.end = end;
            this.searchValue = searchValue;
        }


        @Override
        public Integer call() throws Exception {
            int re = search(searchValue,begin,end);
            return re;
        }
    }

    public static int pSearch(int searchValue) throws ExecutionException, InterruptedException {
        int subArrSize = arr.length/Thread_Num+1;
        List<Future<Integer>> re = new ArrayList<Future<Integer>>();
        for(int i = 0;i<arr.length;i++){
            int end = i +subArrSize;
            if(end>=arr.length){
                end = arr.length;
            }
            re.add(pool.submit(new SearchTask(i,end,searchValue)));
        }
        try {
            for (Future<Integer> fu : re) {
                if (fu.get() >= 0) {
                    return fu.get();
                }
            }
            return -1;
        }finally {
            pool.shutdown();
        }
    }

    public static void main(String[] args) {
        arr = new int[]{3, 4, 6, 8, 9, 1, 7, 15};
        try {
            int Index = pSearch(9);
            System.out.println("Index = "+Index );
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
