package con.java.prepost;

/**
 * Created by Yisa on 2017/2/18.
 */
public class BeanWayService {
    public void init(){
        System.out.println("@Bean-init-method");
    }

    public BeanWayService(){
        super();;
        System.out.println("初始化构造函数-BeanWayService");
    }

    public void destory(){
        System.out.println("@Bean-destory-method");
    }
}
