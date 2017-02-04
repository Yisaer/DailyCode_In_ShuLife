public class Main {

    static  int i = 0;
    public static class PlusTask implements Runnable{

        @Override
        public void run() {
            synchronized (PlusTask.class){
                for(int k = 0;k<10000;k++){
//                    System.out.println(Thread.currentThread().getName() +":"+(i++));
                    i++;
                }
            }
//            for(int k = 0;k<10000;k++){
////                    System.out.println(Thread.currentThread().getName() +":"+(i++));
//                i++;
//            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        PlusTask plusTask = new PlusTask();
        for(int  i = 0;i<10;i++){
            threads[i] = new Thread(new PlusTask(),"Thread"+i);
            threads[i].start();
        }
        for(int i = 0;i<3;i++){
            threads[i].join();
        }

        System.out.println(i);
    }
}
