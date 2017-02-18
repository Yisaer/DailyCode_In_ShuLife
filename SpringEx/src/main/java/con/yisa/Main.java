package con.yisa;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Yisa on 2017/2/18.
 */
public class Main {
    public static void main(String [] args){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DiConfig.class);
        UseFunctionService useFunctionService =
                context.getBean(UseFunctionService.class);
        System.out.println(useFunctionService.SayHello("di"));
        context.close();
    }
}
