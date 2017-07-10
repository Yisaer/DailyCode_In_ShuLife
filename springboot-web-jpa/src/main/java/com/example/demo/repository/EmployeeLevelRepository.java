package com.example.demo.repository;

import com.example.demo.model.EmployeeLevel;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Yisa on 2017/7/9.
 */
public interface EmployeeLevelRepository extends PagingAndSortingRepository<EmployeeLevel,Integer>{
    /**
     * 通过职位名称关键字查询模糊职位信息
     */
    List<EmployeeLevel> findByNameLike(String name);
}
