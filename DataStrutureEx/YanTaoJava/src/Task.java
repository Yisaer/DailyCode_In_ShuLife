import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Task  implements Runnable{

    public static BlockingQueue<Node> bq = new LinkedBlockingQueue<Node>();
    public static boolean Arr[];
    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        Arr = new boolean[20];
        for(int i = 1;i<20;i++){
            Arr[i] = false;
        }
        Node Pre = new Node(0);
        Node root = new Node();
        Node[] NodeArr = new Node[4];
        for(int i = 0;i<4;i++){
            NodeArr[i] = new Node();
        }
        Pre.NextArr[1] = root;
        root.NextArr[0] = NodeArr[0];
        root.NextArr[1] = NodeArr[1];
        NodeArr[0].NextArr[0] = NodeArr[2];
        NodeArr[0].NextArr[1] = NodeArr[3];
        bq.add(Pre);
        Task task = new Task();
        es.execute(task);
        es.shutdown();
        boolean IsCom = true;
        for(int i = 1;i<=5;i++){
           System.out.println(Arr[i]);
            IsCom = IsCom & Arr[i];
        }
        System.out.println(IsCom);
    }

    @Override
    public void run() {


            try {
                Node node = bq.take();
                System.out.println("node.num = "+node.num);
                Arr[node.num] = true;
                for(int i = 0;i<2;i++){
                    if(node.NextArr[i] != null){
                        node.NextArr[i].num = node.num*2+i;
                        System.out.println("childNodenum = "+(node.num*2+i));
                        bq.add(node.NextArr[i]);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


}
