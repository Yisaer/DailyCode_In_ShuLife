import java.util.concurrent.atomic.AtomicReference;

public class Main {

    static AtomicReference<Integer> money = new AtomicReference<Integer>();

    public static void main(String[] args) {
        money.set(19);
        for(int i  =0;i<3;i++){
            new Thread(){
                @Override
                public void run() {
                    while (true){
                        while (true){
                            Integer m =money.get();
                            if(m<20){
                                if(money.compareAndSet(m,m+20)){
                                    System.out.println("重置成功: "+money.get());
                                    break;
                                }

                            }else{
                                System.out.println("无需充值");
                                break;;
                            }
                        }
                    }
                }
            }.start();
            new Thread(){
                @Override
                public void run() {

                }
            }.start();
        }
    }
}
