package con.java.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by Yisa on 2017/2/18.
 */
@Configuration
@ComponentScan("con.java.aop")
@EnableAspectJAutoProxy
public class AopConfig {
}
