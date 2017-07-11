package com.example.demo;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {


	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;

	@Test
	public void test() throws Exception{
		for(int  i =0;i<15;i++){
			productRepository.save(new Product("name"+i));
		}
		Assert.assertEquals(15,productRepository.findAll().size());
	}

	@Test
	public void PageTest() throws Exception{
		List<Product> products = productService.findAll(2,5).getContent();
		for(Product product : products){
			System.out.println(product.getName());
		}

	}

	@Test
	public void contextLoads() {
	}

}
