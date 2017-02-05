public class Main {
    public static  volatile long t = 0;
    public static class ChangeT implements  Runnable{
        private long to;

        ChangeT(long to){
            this.to = to;
        }


        @Override
        public void run() {
            while(true){
                Main.t = to;
                Thread.yield();
            }
        }
    }

    public static class ReadT implements Runnable{

        @Override
        public void run() {
            while(true){
                long t = Main.t;
                if(t != 111L&& t!=-999L&& t!=333L && t!=-444L ){
                    System.out.println(t);
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ChangeT(111L)).start();
        new Thread(new ChangeT(-999L)).start();
        new Thread(new ChangeT(333L)).start();
        new Thread(new ChangeT(-444L)).start();
        new Thread(new ReadT()).start();
    }
}
