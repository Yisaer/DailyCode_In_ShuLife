package con.java.anno;

import org.springframework.stereotype.Service;

/**
 * Created by Yisa on 2017/2/19.
 */
@Service
public class DemoService {
    public void outputResult(){
        System.out.println("从组合注解配置获得的bean");
    }
}
