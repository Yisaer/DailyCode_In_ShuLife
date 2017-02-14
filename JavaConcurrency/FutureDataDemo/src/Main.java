public class Main {

    public static void main(String[] args) {
        Client client = new Client();
        Data data = client.request("name");
        try {
            for(int i = 1;i<=3;i++){
                System.out.println(i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据 = " + data.getData());
    }
}
