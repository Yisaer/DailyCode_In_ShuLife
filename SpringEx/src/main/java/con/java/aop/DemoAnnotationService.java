package con.java.aop;

import org.springframework.stereotype.Service;

/**
 * Created by Yisa on 2017/2/18.
 */

// 使用注解的被拦截类
@Service
public class DemoAnnotationService {
    @Action(name="注解拦截的add操作")
    public void add(){}
}
