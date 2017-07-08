package service;

/**
 * Created by Yisa on 2017/7/8.
 */
public class FooBarServiceImpl implements FoobarSerice {
    public String getMessage(String msg) {
        String output = "FoorBar say :" +msg;
        return output;
    }
}
