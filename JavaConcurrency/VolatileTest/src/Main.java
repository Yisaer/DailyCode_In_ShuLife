public class Main {


    static Integer i = new Integer(0);
    static int e = 0;
    public static class PlusTask implements Runnable{

        @Override
        public void run() {
            synchronized (i){
                for(int k = 0;k<10000;k++){
//                    System.out.println(Thread.currentThread().getName() +":"+(i++));
                    e++;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for(int  i = 0;i<10;i++){
            threads[i] = new Thread(new PlusTask());
            threads[i].start();
        }
        for(int i = 0;i<10;i++){
            threads[i].join();
        }

        System.out.println(e);
    }
}
