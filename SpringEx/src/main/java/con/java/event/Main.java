package con.java.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Yisa on 2017/2/18.
 */
public class Main {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(EventConfig.class);

        DemoPushlisher demoPushlisher = context.getBean(DemoPushlisher.class);
        demoPushlisher.publish("hello application");
        context.close();
    }
}
