public class Main {
    private static volatile boolean  ready ;
    private static volatile int number;

    public static class ReadThread extends Thread{
        @Override
        public void run() {
            while(!ready){
                System.out.println(number);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReadThread().start();
        Thread.sleep(1000);
        number = 42;
        ready = true;
        Thread.sleep(1000);

    }
}
