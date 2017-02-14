import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * Created by Yisa on 2017/2/14.
 */
public class Producer  {
    private final RingBuffer<PCdata> ringBuffer;

    public Producer(RingBuffer<PCdata> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void pushData(ByteBuffer bb){
        long seq = ringBuffer.next();
        try{
            PCdata event = ringBuffer.get(seq);
            event.setValue(bb.getLong(0));
        }finally {
            ringBuffer.publish(seq);
        }

    }
}
