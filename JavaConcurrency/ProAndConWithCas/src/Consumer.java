import com.lmax.disruptor.WorkHandler;

/**
 * Created by Yisa on 2017/2/14.
 */
public class Consumer implements WorkHandler<PCdata> {
    @Override
    public void onEvent(PCdata pCdata) throws Exception {
        System.out.println(Thread.currentThread().getId()+": Event: --" + pCdata.getValue()+ "--");
    }
}
