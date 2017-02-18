package con.java.prepost;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Yisa on 2017/2/18.
 */
@Configuration
@ComponentScan("con.java.prepost")

public class PrePostConfig {
    @Bean(initMethod = "init",destroyMethod = "destory")
    BeanWayService beanWayService(){
        return new BeanWayService();
    }

    @Bean
    JSR250WayService jsr250WayService(){
        return new JSR250WayService();
    }
}
