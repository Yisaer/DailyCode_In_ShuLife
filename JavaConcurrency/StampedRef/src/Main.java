import java.util.concurrent.atomic.AtomicStampedReference;

public class Main {
    static AtomicStampedReference<Integer> money = new AtomicStampedReference<Integer>(19,0);

    public static void main(String[] args) {
        for(int i=0;i<3;i++){
            final int timestamp = money.getStamp();
            new Thread(){
                @Override
                public void run() {
                    while(true){
                        while(true){
                            Integer m = money.getReference();
                            if( m < 20){
                                if(money.compareAndSet(m,m+20,timestamp,timestamp+1)){
                                    System.out.println("余额小于20元,充值成功:" + money.getReference());
                                    break;
                                }
                            }else{
                                System.out.println("无需充值");
                                break;
                            }
                        }
                    }
                }
            }.start();
        }
        new Thread(){
            @Override
            public void run() {
                for(int i = 0;i<100;i++){
                    while(true){
                        int timestamp = money.getStamp();
                        Integer m = money.getReference();
                        if(m > 10){
                            System.out.println("大于十元");
                            if(money.compareAndSet(m,m-10,timestamp,timestamp+1)){
                                System.out.println("成功消费10元: "+ money.getReference());
                                break;
                            }
                        }
                        else{
                            System.out.println("不够");
                            break;
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

}
