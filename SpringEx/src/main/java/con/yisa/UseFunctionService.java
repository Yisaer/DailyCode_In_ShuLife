package con.yisa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Yisa on 2017/2/18.
 */
@Service
public class UseFunctionService {
    @Autowired
    FunctionService functionService;

    public String SayHello(String word){
        return functionService.sayHello(word);
    }
}
