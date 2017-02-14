import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Yisa on 2017/2/14.
 */
public class Consumer implements Runnable {
    private BlockingQueue<PCData> queue;
    private static final int SLEEPTIME = 1000;

    public Consumer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("start Consumer id = " + Thread.currentThread().getId());
        Random r = new Random();
        try {
            while (true) {
                PCData data = queue.take();
                if(null!=data){
                    int re = data.getData() * data.getData();
                    System.out.printf("%d * %d = %d\n",data.getData(),data.getData(),re);
                    Thread.sleep(r.nextInt(SLEEPTIME));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
