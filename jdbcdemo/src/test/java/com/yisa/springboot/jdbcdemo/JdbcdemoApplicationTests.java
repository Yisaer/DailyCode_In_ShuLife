package com.yisa.springboot.jdbcdemo;

import com.yisa.springboot.jdbcdemo.dao.Impl.UserDaoImpl;
import com.yisa.springboot.jdbcdemo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcdemoApplicationTests {

	@Autowired
	private UserDaoImpl userDao;

	@Test
	public void testAdd(){
		User user = new User(15,"User");
		userDao.addUser(user);
	}

	@Test
	public void testUpdate(){
		User user = new User(11,15,"NewUser");
		userDao.updateUser(user);

	}

	@Test
	public void testgetList(){
		List<User> userList = userDao.getUserList();
		for(User u : userList){
			System.out.println(u);
		}
	}

	@Test
	public void contextLoads() {
	}

}
