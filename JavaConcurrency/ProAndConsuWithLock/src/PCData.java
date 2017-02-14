/**
 * Created by Yisa on 2017/2/14.
 */
public final class PCData {
    private int Data;

    public PCData(int data) {
        Data = data;
    }
    public PCData(String d){
        this.Data = Integer.valueOf(d);
    }

    public int getData() {
        return Data;
    }

    @Override
    public String toString() {
        return "data:" + Data;
    }
}

