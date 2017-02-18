package con.java.prepost;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Yisa on 2017/2/18.
 */
public class Main {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context  =
                new AnnotationConfigApplicationContext(PrePostConfig.class);

        BeanWayService beanWayService = context.getBean(BeanWayService.class);

        JSR250WayService jsr250WayService = context.getBean(JSR250WayService.class);
        context.close();
    }
}
