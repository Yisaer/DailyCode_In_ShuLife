import java.util.Random;
import java.util.concurrent.*;

public class Main {

    public static final int GEN_COUNT = 10000000;
    public static final int Thread_Count = 4;
    static ExecutorService exe = Executors.newFixedThreadPool(Thread_Count);
    public static Random rnd = new Random(123);
    public static ThreadLocal<Random> tRnd = new ThreadLocal<Random>(){
        @Override
        protected Random initialValue() {
            return new Random(123);
        }
    };
    public static class RndTask implements Callable<Long> {
        private int mode = 0;

        public RndTask(int mode) {
            this.mode = mode;
        }
        public Random getRandom(){
            if(mode == 0){
                return rnd;
            }
            else if(mode == 1){
                return tRnd.get();
            }else{
                return null;
            }
        }

        @Override
        public Long call() throws Exception {
            long b = System.currentTimeMillis();
            for(long  i =0;i<GEN_COUNT;i++){
                getRandom().nextInt();
            }
            long e = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName()+" spned " +(e-b) +"ms");
            return e-b;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<Long>[] futs = new Future[Thread_Count];
        for(int i=0;i<Thread_Count;i++){
            futs[i] = exe.submit(new RndTask(0));
        }
        long totaltime = 0;
        for(int i = 0;i<Thread_Count;i++){
            totaltime += futs[i].get();
        }
        System.out.println("多线程访问一个Random实例:" + totaltime+"ms");
        for(int i = 0;i<Thread_Count;i++){
            futs[i] = exe.submit(new RndTask(1));
        }
        totaltime = 0;
        for(int i = 0;i<Thread_Count;i++){
            totaltime += futs[i].get();
        }
        System.out.println("使用Threadlocal包装Random实例: "+ totaltime+"ms");
        exe.shutdown();
    }
}
