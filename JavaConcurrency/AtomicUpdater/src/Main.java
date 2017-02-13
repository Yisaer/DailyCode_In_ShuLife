import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class Main {

    public static class Candidate{
        int id;
        volatile int score;
    }
    public final static AtomicIntegerFieldUpdater<Candidate> scoreUpdater
            = AtomicIntegerFieldUpdater.newUpdater(Candidate.class,"score");
    public static AtomicInteger allScore = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        final Candidate stu = new Candidate();
        Thread [] t = new Thread[10000];
        for(int i = 0;i < 10000;i++){
            t[i] = new Thread(){
                @Override
                public void run() {
                    if(Math.random() > 0.4){
                        scoreUpdater.incrementAndGet(stu);
                        allScore.incrementAndGet();
                    }
                }
            };
            t[i].start();
        }
        for(int i = 0;i<10000;i++){
            t[i].join();
        }
        System.out.println("score = "+stu.score);
        System.out.println("allScoure = "+allScore);
    }
}
