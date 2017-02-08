import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockCondition implements Runnable{

    public static ReentrantLock lock1 = new ReentrantLock();
    public static Condition condition = lock1.newCondition();

    public static void main(String[] args) {
        LockCondition T = new LockCondition();
        Thread t1 = new Thread(T);
        t1.start();

        lock1.lock();
        condition.signal();
        lock1.unlock();
    }

    @Override
    public void run() {
        try{
            lock1.lock();
            System.out.println("Thread is await");
            condition.await();
            System.out.println("Thread is going on");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock1.unlock();
        }
    }
}
