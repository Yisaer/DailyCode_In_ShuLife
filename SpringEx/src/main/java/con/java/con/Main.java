package con.java.con;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Yisa on 2017/2/19.
 */
public class Main  {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context =
               new AnnotationConfigApplicationContext(ConConfig.class);
        ListService listService =  context.getBean(ListService.class);
        System.out.println(context.getEnvironment().getProperty("os.name")+"系统下的命令为"+
            listService.showListCmd());
    }
}
