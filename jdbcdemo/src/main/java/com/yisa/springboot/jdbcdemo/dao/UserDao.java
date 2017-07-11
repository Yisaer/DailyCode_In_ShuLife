package com.yisa.springboot.jdbcdemo.dao;

import com.yisa.springboot.jdbcdemo.domain.User;

import java.util.List;

/**
 * Created by Yisa on 2017/7/11.
 */
public interface UserDao {
    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUser(User user);
    public User findUser(int id);
    public List<User> getUserList();
}
