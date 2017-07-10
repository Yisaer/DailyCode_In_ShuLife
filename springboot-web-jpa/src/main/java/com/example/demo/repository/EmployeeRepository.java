package com.example.demo.repository;

import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Yisa on 2017/7/9.
 */
public interface EmployeeRepository extends JpaRepository<Employee,Long>,JpaSpecificationExecutor<Employee>{
    /**
     * 通过员工编号修改员工性别
     * @param id    编号
     * @param gender    性别
     * @return  修改影响的行数
     */
    @Modifying
    @Query(value = "UPDATE m_employee e SET e.emp_gender = :gender where e.emp_id = :id",nativeQuery = true)
    Integer updateEmployeeGender(@Param("id") Long id,@Param("gender") Integer gender);

    /**
     * 通过员工编号修改员工手机号
     * @param id
     * @param phone
     * @return
     */
    @Modifying
    @Query(value = "UPDATE m_employee e SET e.emp_phone = ?2 WHERE  e.emp_id= ?1",nativeQuery = true)
    Integer updateEmployeePhone(Long id , String phone);
}
