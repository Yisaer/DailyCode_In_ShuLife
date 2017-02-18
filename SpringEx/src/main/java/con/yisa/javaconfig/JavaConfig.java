package con.yisa.javaconfig;

import con.yisa.UseFunctionService;
import org.springframework.context.annotation.Bean;

/**
 * Created by Yisa on 2017/2/18.
 */
public class JavaConfig {
    @Bean
    public FunctionService functionService(){
        return new FunctionService();
    }

    @Bean
    public UseFunction useFunction(){
        UseFunction useFunction = new UseFunction();
        useFunction.setFunctionService(functionService());
        return useFunction;
    }

    @Bean
    public UseFunction useFunction(FunctionService functionService){
        UseFunction useFunction = new UseFunction();
        useFunction.setFunctionService(functionService);
        return useFunction;
    }
}
