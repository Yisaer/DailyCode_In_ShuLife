import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Yisa on 2017/2/15.
 */
public class ParallSort {
    static ExecutorService pool = Executors.newCachedThreadPool();
    static int exchFlag = 1;
    static synchronized void setExchFlag(int v){
        exchFlag = v;
    }
    static int getExchFlag(){
        return exchFlag;
    }
    static int[] arr;

    public ParallSort(int[] Arr) {
        arr = Arr;
    }

    public static class OddEvenSortTask implements Runnable{
        int i;
        CountDownLatch latch;

        public OddEvenSortTask(int i, CountDownLatch latch) {
            this.i = i;
            this.latch = latch;
        }

        @Override
        public void run() {
            if(arr[i]>arr[i+1]){
                int tmp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = tmp;
                setExchFlag(1);
            }
            latch.countDown();
        }
    }

    public static void pOddEvenSort() throws InterruptedException {
        int start = 0;
        while(getExchFlag() == 1|| start == 1){
            setExchFlag(0);
            int num = arr.length/2 - (arr.length%2==0?start:0);
            CountDownLatch countDownLatch = new CountDownLatch(num);
            for(int i = start ;i<arr.length-1;i+=2){
                pool.submit(new OddEvenSortTask(i,countDownLatch));

            }
            countDownLatch.await();
            if(start == 0){
                start = 1;
            }
            else{
                start = 0;
            }
        }
        pool.shutdown();
    }
}
