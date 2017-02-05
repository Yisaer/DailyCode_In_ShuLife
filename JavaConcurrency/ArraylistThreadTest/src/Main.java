import java.util.ArrayList;
import java.util.Vector;

public class Main {

    static ArrayList<Integer> al = new ArrayList<Integer>(10);
    static Vector<Integer> vl = new Vector<Integer>(10);
    public static class Add implements Runnable{

        @Override
        public void run() {
            for(int i  =0;i<1000000;i++){
                vl.add(i);
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
            Thread t1 = new Thread(new Add());
            Thread t2 = new Thread(new Add());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(vl.size());
    }
}
