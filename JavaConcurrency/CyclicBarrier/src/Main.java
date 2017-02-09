import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main {

    public static class Soldier implements Runnable{
        private String Solider;
        private final CyclicBarrier cyclicBarrier;

        Soldier(String solider,CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
            this.Solider = solider;
        }


        @Override
        public void run() {
            try {
                cyclicBarrier.await();
                doWork();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }

        private void doWork() {
            try {
                Thread.sleep(new Random().nextInt(10)*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Solider + "任务完成");
        }
    }
    public static class BarrierRun implements Runnable{
        boolean flag ;
        int N;

        public BarrierRun(boolean flag, int n) {
            this.flag = flag;
            N = n;
        }

        @Override
        public void run() {
            if(flag){
                System.out.println("司机: [士兵" + N+"个 任务完成");
            }else{
                System.out.println("司令: [士兵" + N+"个 集合完毕");
                flag  =true;
            }
        }
    }

    public static void main(String[] args) {
        final int N = 10;
        Thread []  allSoldier = new Thread[N];
        boolean flag = false;
        CyclicBarrier cyclic = new CyclicBarrier(N,new BarrierRun(flag,N));
        // 设置屏障
        System.out.println("集合队伍");
        for(int i = 0;i<N;i++){
            System.out.println("士兵 "+ i +" 报道 ");
            allSoldier[i] = new Thread(new Soldier("士兵" + i,cyclic));
            allSoldier[i].start();
        }
    }
}
