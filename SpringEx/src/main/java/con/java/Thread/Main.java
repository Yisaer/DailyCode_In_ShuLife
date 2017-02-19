package con.java.Thread;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Yisa on 2017/2/19.
 */
public class Main {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TaskConfig.class);
        TaskService service = context.getBean(TaskService.class);

        for(int i = 0;i<10;i++){
            service.exeAsyncTask(i);
            service.exeAsyncTaskPlus(i);
        }
        context.close();
    }
}
