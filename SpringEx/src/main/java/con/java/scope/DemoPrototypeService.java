package con.java.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by Yisa on 2017/2/18.
 */
@Service
@Scope("prototype")
public class DemoPrototypeService {
}
