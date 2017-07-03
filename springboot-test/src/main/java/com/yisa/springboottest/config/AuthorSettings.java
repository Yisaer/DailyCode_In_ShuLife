package com.yisa.springboottest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Yisa on 2017/7/3.
 */


@Component
@ConfigurationProperties(prefix = "author")
public class AuthorSettings {
    private String name ;
    private Long age ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}
