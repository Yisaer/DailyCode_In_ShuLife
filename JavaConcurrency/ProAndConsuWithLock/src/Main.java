import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<PCData> queue = new LinkedBlockingQueue<PCData>(10);
        Producer pro1 = new Producer(queue);
        Producer pro2 = new Producer(queue);
        Producer pro3 = new Producer(queue);
        Consumer con1 = new Consumer(queue);
        Consumer con2 = new Consumer(queue);
        Consumer con3 = new Consumer(queue);
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(pro1);
        es.execute(pro2);
        es.execute(pro3);
        es.execute(con1);
        es.execute(con2);
        es.execute(con3);
        Thread.sleep(10*1000);
        pro1.stop();
        pro2.stop();
        pro3.stop();
        System.out.println("here3");
        Thread.sleep(3000);
        System.out.println("here");
        es.shutdown();
        System.out.println("here2");
    }
}
