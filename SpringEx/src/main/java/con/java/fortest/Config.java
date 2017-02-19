package con.java.fortest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Yisa on 2017/2/19.
 */
@Configuration
public class Config {
    @Bean
    @Profile("dev")
    public TestBean devTestBean(){
        return new TestBean(" from dev profile");
    }
    @Bean
    @Profile("prod")
    public TestBean proTestbean(){
        return new TestBean("from pro profile");
    }
}
