/**
 * Created by Yisa on 2017/2/22.
 */
public class Main {
    public static void main(String[] args){
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
        NodeTask.bq.add(Pre);
        NodeTask.es.execute(new NodeTask());
        while(NodeTask.bq.size() != 0 || NodeTask.es.isTerminated() == false) {

        };

        boolean IsCom = true;
        for(int i =1;i<=5;i++){
            IsCom = IsCom & NodeTask.Arr[i];
        }
        System.out.println(IsCom);


    }
}
