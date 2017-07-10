package com.example.demo.model.condition;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;

/**
 * Created by Yisa on 2017/7/9.
 */

@Data
@RequiredArgsConstructor
public class EmployeeCondition {

    String name ;

    @Column(name = "emp_phone",length = 11)
    String phone;

    @Column(name = "emp_identity",length = 18)
    String identity;

    @Column(name = "emp_gender")
    Integer gender;

    @Column(name = "emp_status")
    Boolean status;

    String deptName;
    String levelName;


}
