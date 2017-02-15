import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Yisa on 2017/2/15.
 */
public class Div implements Runnable {

    public static BlockingQueue<Msg> bq = new LinkedBlockingQueue<Msg>();

    @Override
    public void run() {
        while(true){
            try {
                Msg msg = bq.take();
                msg.i = msg.i/2;
                System.out.println(msg.orgStr+" = "+msg.i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
