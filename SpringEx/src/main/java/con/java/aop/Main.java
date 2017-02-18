package con.java.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Yisa on 2017/2/18.
 */

public class Main {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AopConfig.class);
        DemoAnnotationService demoAnnotationService =
                context.getBean(DemoAnnotationService.class);
//        DemoMethodService demoMethodService =
//                context.getBean(DemoMethodService.class);
        demoAnnotationService.add();
//        demoAnnotationService.add();
//        demoMethodService.add();
        context.close();
    }
}
