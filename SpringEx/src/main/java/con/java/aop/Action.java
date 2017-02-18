package con.java.aop;

import java.lang.annotation.*;

/**
 * Created by Yisa on 2017/2/18.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
    String name();
}
