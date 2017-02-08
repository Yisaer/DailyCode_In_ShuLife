import java.util.Objects;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteDemo {

    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    private int value;

    public Object handleRead(Lock lock) throws InterruptedException {

        try{
            lock.lock();
            Thread.sleep(1000);
            return value;
        }finally {
            lock.unlock();
        }

    }

    public void handleWrite(Lock lock ,int index) throws InterruptedException {
        try{
            lock.lock();
            Thread.sleep(1000);
            value = index;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReadWriteDemo demo = new ReadWriteDemo();
        Runnable readRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(demo.handleRead(readLock));

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    demo.handleWrite(writeLock,new Random().nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        for(int i  =0;i<18;i++){
            new Thread(readRunnable).start();
        }
        for(int i=0;i<2;i++){
            new Thread(writeRunnable).start();
        }
    }
}
