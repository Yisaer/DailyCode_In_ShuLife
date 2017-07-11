package com.example.demo.service;

import com.example.demo.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Yisa on 2017/7/10.
 */
public interface ProductService {


    Iterable<Product> listAllProducts();
    Product getProductById(Integer id);
    Product saveProduct(Product product);
    void deleteProduct(Integer id);
    Page<Product> findAll(int PageNumber ,int pageSize);
}
