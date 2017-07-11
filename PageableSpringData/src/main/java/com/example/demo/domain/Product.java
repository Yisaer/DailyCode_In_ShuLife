package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Created by Yisa on 2017/7/10.
 */

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected Product() {
    }

    public Product(String name) {
        this.name = name;
    }
}
