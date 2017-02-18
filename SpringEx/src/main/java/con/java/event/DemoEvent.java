package con.java.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by Yisa on 2017/2/18.
 */
public class DemoEvent extends ApplicationEvent {

    public static final long serialVersionUID = 1L;
    private String msg;


    public DemoEvent(Object source,String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
