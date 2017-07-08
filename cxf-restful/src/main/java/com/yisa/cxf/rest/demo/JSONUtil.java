package com.yisa.cxf.rest.demo;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * Created by Yisa on 2017/7/8.
 */
public class JSONUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static <T> String toJSON(T obj){
        String jsonStr = null;
        try{
            jsonStr = objectMapper.writeValueAsString(obj);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonStr;
    }
}
