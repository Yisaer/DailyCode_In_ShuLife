import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TraceThreadPool extends ThreadPoolExecutor{


    public TraceThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                           BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);

    }

    @Override
    public void execute(Runnable command) {
        super.execute(wrap(command,clientTrace(),Thread.currentThread().getName()));
    }

    private Exception clientTrace(){
        return new Exception("Client statck Trace");
    }
    private Runnable wrap(final Runnable task,final Exception clientStack,String clientName){
        return new Runnable() {
            @Override
            public void run() {
                try{
                    task.run();
                }catch (Exception e){
                    e.printStackTrace();
                    throw e;
                }
            }
        };
    }
    public static class DivTask implements Runnable{
        int a,b;

        public DivTask(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            double re = a/b;
            System.out.println(re);
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor pool = new TraceThreadPool(0,Integer.MAX_VALUE,0L,TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
        for(int i = 0;i<5;i++){
            pool.execute(new DivTask(100,i));
        }
    }
}
