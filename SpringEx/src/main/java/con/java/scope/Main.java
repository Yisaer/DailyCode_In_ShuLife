package con.java.scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Yisa on 2017/2/18.
 */
public class Main {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ScopeConfig.class);
        DemoSingleton s1 = context.getBean(DemoSingleton.class);
        DemoSingleton s2 = context.getBean(DemoSingleton.class);

        DemoPrototypeService p1 = context.getBean(DemoPrototypeService.class);
        DemoPrototypeService p2 = context.getBean(DemoPrototypeService.class);


        System.out.println("s1 与 s2是否相当" + s1.equals(s2));
        System.out.println("p1 与 p2是否相当" + p1.equals(p2));
        context.close();
    }
}
