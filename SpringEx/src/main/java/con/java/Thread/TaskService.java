package con.java.Thread;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by Yisa on 2017/2/19.
 */
@Service
public class TaskService  {
    @Async
    public void exeAsyncTask(Integer i ){
        System.out.println("执行异步任务:" + i );
    }
    @Async
    public void exeAsyncTaskPlus(Integer i ){
        System.out.println("异步任务+1:"+ (i+1));
    }
}
