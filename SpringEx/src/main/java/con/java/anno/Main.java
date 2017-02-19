package con.java.anno;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Yisa on 2017/2/19.
 */
public class Main {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context  =
                new AnnotationConfigApplicationContext(DemoConfig.class);
        DemoService demoService = context.getBean(DemoService.class);
        demoService.outputResult();
        context.close();
    }
}
