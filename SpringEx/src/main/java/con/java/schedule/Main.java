package con.java.schedule;

import con.java.Thread.TaskConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Yisa on 2017/2/19.
 */
public class Main {
    public static void main(String[] args){
        AnnotationConfigApplicationContext contet =
                new AnnotationConfigApplicationContext(SchConfig.class);

    }
}
