/**
 * Created by Yisa on 2017/2/14.
 */
public class FutureData implements Data {
    protected RealData realdata = null;
    protected boolean isReady = false;

    public synchronized void setRealData(RealData realdata){
        if(isReady){
            return ;
        }
        this.realdata = realdata;
        isReady = true;
        notifyAll();
    }

    @Override
    public synchronized String getData() {
        while(!isReady){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realdata.result;
    }
}
