import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main implements Runnable{

    public static final CountDownLatch end = new CountDownLatch(10);
    public static final Main  demo = new Main();

    public static void main(String[] args) throws InterruptedException {
//
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for(int i = 0;i<10;i++){
            exec.submit(demo);
        }
        end.await();
        //
        System.out.println("fire");
        exec.shutdown();
    }

    @Override
    public void run() {
        try{
            Thread.sleep(new Random().nextInt(10)*1000);
            end.countDown();
            System.out.println("check Fin");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
