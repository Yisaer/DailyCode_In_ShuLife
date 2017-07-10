package com.example.demo.repository;

import com.example.demo.model.Dept;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Yisa on 2017/7/9.
 */
public interface DeptRepository extends CrudRepository<Dept,Integer>{

    /**
     * 通过关键字模糊查询
     *
     * @param name
     * @return
     */
    List<Dept> findByNameLike(String name);
}
