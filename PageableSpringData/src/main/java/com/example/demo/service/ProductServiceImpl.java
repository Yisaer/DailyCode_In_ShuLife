package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Yisa on 2017/7/10.
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    private PageRequest buildPageRequest(int pageNumber,int pageSize){
        return new PageRequest(pageNumber-1,pageSize);
    }

    @Override
    public Iterable<Product> listAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findOne(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.delete(id);
    }

    @Override
    public Page<Product> findAll(int pageNumber ,int pageSize) {
        PageRequest request = this.buildPageRequest(pageNumber,pageSize);
        return productRepository.findAll(request);
    }
}
