package com.yisa.dao;

import com.yisa.domain.User;
import com.yisa.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Yisa on 2017/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/context.xml")
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void test() throws Exception{
        List<User> users = userService.getUser();
        for(User u : users){
            System.out.println(u.getName());
        }
    }
}
