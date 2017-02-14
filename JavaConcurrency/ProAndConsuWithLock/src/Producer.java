import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Yisa on 2017/2/14.
 */
public class Producer implements Runnable {
    private volatile boolean isRunning =  true;
    private BlockingQueue<PCData> queue;    //内存缓冲区
    private static AtomicInteger count = new AtomicInteger(); //总数
    private static final int SLEEPTIME = 1000;

    public Producer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        PCData data = null;
        Random r = new Random();
        System.out.println(" start pro id = "+ Thread.currentThread().getId());
        try {
            while(isRunning){
                Thread.sleep(r.nextInt(SLEEPTIME));
                data = new PCData(count.incrementAndGet());
                System.out.println(data +" is in queue");
                if( !queue.offer(data,2, TimeUnit.SECONDS)){
                    System.out.println(" put data error");
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
    public void stop(){
        this.isRunning = false;
    }
}
