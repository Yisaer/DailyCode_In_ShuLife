import java.util.concurrent.Callable;

/**
 * Created by Yisa on 2017/2/15.
 */
public class RealData implements Callable<String> {

    private String para;

    public RealData(String para) {
        this.para = para;
    }

    @Override
    public String call() throws Exception {
        StringBuffer sb = new StringBuffer();
        for(int i = 0;i<10;i++){
            sb.append(para);
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
