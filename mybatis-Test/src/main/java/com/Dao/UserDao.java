package com.Dao;

import com.DBAccess.DBAccess;
import com.model.User;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;


/**
 * Created by Yisa on 2017/2/25.
 */
public interface UserDao {
    User getUser(int id);
    void insertUser(User user);
    void deleteUser(int id);
    void updateUser(User user);
}
