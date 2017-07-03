package com.yisa.springboottest;

import com.yisa.springboottest.config.AuthorSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class SpringbootTestApplication {

	@Autowired
	private AuthorSettings authorSettings;

	@RequestMapping("/")
	String index(){
		return " name is:"+authorSettings.getName()+" and age is:"+ authorSettings.getAge();
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringbootTestApplication.class, args);
	}
}
