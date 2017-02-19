package con.java.con;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Yisa on 2017/2/19.
 */
@Configuration
public class ConConfig {

    @Bean
    @Conditional(WindowsCondition.class)
    public ListService windowService(){
        return new WindowService();
    }

    @Bean
    @Conditional(LinuxCondition.class)
    public ListService osService(){
        return new OSService();
    }
}
