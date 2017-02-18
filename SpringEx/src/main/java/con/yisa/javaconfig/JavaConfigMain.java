package con.yisa.javaconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Yisa on 2017/2/18.
 */
public class JavaConfigMain {
    public static void main(String []args){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(JavaConfig.class);
        UseFunction useFunction = context.getBean(UseFunction.class);
        System.out.println(useFunction.SayHello("java config"));
        context.close();
    }
}
