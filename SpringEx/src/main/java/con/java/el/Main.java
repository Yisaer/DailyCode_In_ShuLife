package con.java.el;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Yisa on 2017/2/18.
 */
public class Main {
    public static void main(String[] args){
        AnnotationConfigApplicationContext
                context =
                new AnnotationConfigApplicationContext(ELConfig.class);
        ELConfig resourceService = context.getBean(ELConfig.class);
        resourceService.outputResource();
        context.close();
    }
}
