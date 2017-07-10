package com.example.demo.repository;

import com.example.demo.model.EmployeeDetail;
import org.springframework.data.repository.Repository;

/**
 * Created by Yisa on 2017/7/9.
 */
public interface EmployeeDetailRepository extends Repository<EmployeeDetail,Long>{
    /**
     * 持久化登录信息
     * 如果信息已经存在则修改,否则新增
     */
    EmployeeDetail save(EmployeeDetail employeeDetail);

    /**
     * 通过登录信息编号删除登录信息
     */
    void delete(Long id);

    /**
     * 通过登录信息编号查询登录信息
     */
    EmployeeDetail findOne(Long id);
}
