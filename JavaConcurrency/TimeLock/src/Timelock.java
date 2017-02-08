import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Timelock  implements Runnable{

    public static ReentrantLock lock = new ReentrantLock();


    public static void main(String[] args) {
        Timelock T = new Timelock();
        Thread t1 = new Thread(T);
        Thread t2 = new Thread(T);
        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        try{
            if(lock.tryLock(5, TimeUnit.SECONDS)){
                Thread.sleep(6000);
            }else{
                System.out.println("get lock failed");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            if(lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }
}
