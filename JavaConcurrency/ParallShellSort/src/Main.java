import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static ExecutorService pool = Executors.newCachedThreadPool();

    public static class ParallShell implements Runnable{
        int i = 0;
        int h = 0;
        CountDownLatch latch;
        int[] arr;

        public ParallShell(int i, int h, CountDownLatch latch,int[] Arr) {
            this.i = i;
            this.h = h;
            this.latch = latch;
            arr = Arr;
        }


        @Override
        public void run() {
            if(arr[i]<arr[i-h]){
                int tmp = arr[i];
                int j = i -h;
                while(j>=0 && arr[j] > tmp){
                    arr[j+h] = arr[j];
                    j -= h;
                }
                arr[j+h] = tmp;
            }
            latch.countDown();

        }
    }
    public static void pShellSort(int[] arr){
        int  h =1;
        CountDownLatch latch = null;
        while( h <= arr.length/3){
            h = h*3+1;
        }
        while(h > 0){
            System.out.println("h = " + h);
            if( h >= 4){
                latch = new CountDownLatch(arr.length-h);
            }
            for(int i  =h ;i<arr.length;i++){
                if( h >=4){
                    pool.execute(new ParallShell(i,h,latch,arr));
                }
                else{
                    if(arr[i]<arr[i-h]){
                        int tmp = arr[i];
                        int j = i-h;
                        while(j>=0&&arr[j]>tmp){
                            arr[j+h] = arr[j];
                            j-= h ;
                        }
                        arr[j+h] = tmp;
                    }
                }
            }
            latch.countDown();
            h = (h-1)/3;

        }
        pool.shutdown();
    }

    public static void main(String[] args) {
        int[] arr = { 4, 7,5,6,2,1,9,12,3,84,23,12,43,5,6,3,9,0,-1,44,21,68};
        pShellSort(arr);
        for (int num : arr){
            System.out.print(num+ " ");
        }
    }
}
