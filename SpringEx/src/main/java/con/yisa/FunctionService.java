package con.yisa;

import org.springframework.stereotype.Service;

/**
 * Created by Yisa on 2017/2/18.
 */

@Service
public class FunctionService {
    public String sayHello(String word){
        return "Hello "  + word +" !";
    }
}
