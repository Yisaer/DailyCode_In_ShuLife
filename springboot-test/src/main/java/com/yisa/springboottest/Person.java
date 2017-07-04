package com.yisa.springboottest;

/**
 * Created by Yisa on 2017/7/4.
 */
public class Person {
    private String name;
    private Integer age;

    public Person(){
        super();
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

