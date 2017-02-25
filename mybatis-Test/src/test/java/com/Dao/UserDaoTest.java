package com.Dao;

import com.DBAccess.DBAccess;
import com.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Yisa on 2017/2/25.
 */
public class UserDaoTest {

    private static SqlSessionFactory sqlSessionFactory = null;
    static {
        sqlSessionFactory = DBAccess.getSqlSessionFactory();
    }


    @Test
    public void deleteUser() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);

        try{
            userDao.deleteUser(3);
            sqlSession.commit();
            System.out.println("Succes");
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void updateUser() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        try{
            User user = new User(2,"Quiz");
            userDao.updateUser(user);
            sqlSession.commit();
            System.out.println("Success");
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void insertUser() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User("Kate");

        try{
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            userDao.insertUser(user);
            sqlSession.commit();
            System.out.println("Success");
        }finally {
            sqlSession.close();
        }
    }


    @Test
    public void getUser() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.getUser(1);
        System.out.println(user.getName());

    }

}