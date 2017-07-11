package com.example.demo.repository;

import com.example.demo.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Yisa on 2017/7/10.
 */
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Page<Product> findAll(Pageable pageable);
}
