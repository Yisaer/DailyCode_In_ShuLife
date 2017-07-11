package com.yisa.springboot.jdbcdemo.dao.Impl;

import com.yisa.springboot.jdbcdemo.dao.UserDao;
import com.yisa.springboot.jdbcdemo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yisa on 2017/7/11.
 */

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void addUser(User user) {
        jdbcTemplate.update("insert into user (age,name) value(?,?)",
                new Object[]{user.getAge(),user.getName()});
    }

    @Override
    public void updateUser(User user) {
        jdbcTemplate.update("update user SET age = ?,name  = ? where id = ?",
                new Object[] { user.getAge(),user.getName(),user.getId()});
    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public User findUser(int id) {
        return null;
    }

    @Override
    public List<User> getUserList() {
        List<User> users = jdbcTemplate.query("select * from user where age <= ? "
                ,new Object[]{60}
                ,new BeanPropertyRowMapper<User>(User.class));
        return users;
    }
}
