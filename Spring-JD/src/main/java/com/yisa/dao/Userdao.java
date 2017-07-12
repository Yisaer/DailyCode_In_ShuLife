package com.yisa.dao;

import com.yisa.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yisa on 2017/7/12.
 */


@Repository
public class Userdao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getUserList() {
        List<User> list = jdbcTemplate.query(
                "select * from user",new BeanPropertyRowMapper<User>(User.class)
        );
        return list;
    }
}
