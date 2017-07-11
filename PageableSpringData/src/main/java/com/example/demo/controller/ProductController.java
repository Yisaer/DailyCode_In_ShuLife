package com.example.demo.controller;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Yisa on 2017/7/10.
 */

@RestController
@RequestMapping("/demo")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/product/{number}and{size}",method = RequestMethod.GET)
    public List<Product> getProductList(@PathVariable int number , @PathVariable int size){
        int pageNumber = number;
        int pageSize = size;
        Page<Product> productPage = productService.findAll(pageNumber,pageSize);
        return productPage.getContent();
    }


}
