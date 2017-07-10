package com.example.demo.model.vo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

/**
 * Created by Yisa on 2017/7/9.
 */

@Data
@RequiredArgsConstructor
public class EmployeeVo {
    Long id;
    String name;
    String password;
    String phone;
    String identity;
    String gender;
    String birthday;
    Integer age;
    String address;
    Boolean status;
    Date entryDate;
    Date turnoverDate;
    String deptName;
    String levelname;
    String loginIp;
    Integer loginCount;
}
