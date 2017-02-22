import java.util.concurrent.*;

/**
 * Created by Yisa on 2017/2/22.
 */
public class NodeTask implements Runnable{
    public static BlockingQueue<Node> bq = new LinkedBlockingQueue<Node>();
    public static boolean[] Arr = new boolean[10];
    public static ThreadPoolExecutor es = new ThreadPoolExecutor(5,8,1,
            TimeUnit.SECONDS,new LinkedBlockingDeque<>());

    static {
        for(int i =0;i<10;i++){
            Arr[i] = false;
        }
    }
    @Override
    public void run() {
        try {
            Node node = bq.take();
            Arr[node.num] = true;
            for(int i = 0;i<2;i++)
                if (node.NextArr[i] != null) {
                    int num = node.num * 2 + i;
                    node.NextArr[i].num = num;
                    bq.add(node.NextArr[i]);
                    es.execute(new NodeTask());
                }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
