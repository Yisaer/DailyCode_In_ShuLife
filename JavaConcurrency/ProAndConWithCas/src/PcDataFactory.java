import com.lmax.disruptor.EventFactory;

/**
 * Created by Yisa on 2017/2/14.
 */
public class PcDataFactory implements EventFactory<PCdata> {
    @Override
    public PCdata newInstance() {
        return new PCdata();
    }
}
