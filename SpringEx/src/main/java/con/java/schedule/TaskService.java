package con.java.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yisa on 2017/2/19.
 */
@Service
public class TaskService {
    public static final SimpleDateFormat dateFormat =
            new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime(){
        System.out.println("每个5秒执行一次: "+dateFormat.format(new Date()));
    }


}
