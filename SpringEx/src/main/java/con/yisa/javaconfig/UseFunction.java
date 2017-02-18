package con.yisa.javaconfig;

/**
 * Created by Yisa on 2017/2/18.
 */
public class UseFunction {
    FunctionService functionService;

    public void setFunctionService(FunctionService functionService) {
        this.functionService = functionService;
    }
    public String SayHello(String word){
        return functionService.sayHello(word);
    }
}
