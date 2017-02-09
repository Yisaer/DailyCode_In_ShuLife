import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExThreadPool {


    public static class MyTask implements Runnable{

        public String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("正在执行 ThreadID:" +Thread.currentThread().getId()+", TaskName = "+name);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = new ThreadPoolExecutor(5,5,0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>()){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                super.beforeExecute(t, r);
                System.out.println("准备执行"+ ((MyTask) r ).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                System.out.println("执行完成"+ ((MyTask) r ).name);
            }

            @Override
            protected void terminated() {
                super.terminated();
                System.out.println("线程池退出");
            }
        };
        for(int i = 0;i<5;i++){
            MyTask task = new MyTask("Task-"+i);
            es.execute(task);
            Thread.sleep(10);
        }
        es.shutdown();
    }
}
