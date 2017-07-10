package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Yisa on 2017/7/9.
 */

@Entity
@Table(name = "m_employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @Column(name = "emp_id")
    @GeneratedValue
    private Long id;

    @Column(name = "emp_name",length = 10)
    String name;

    @Column(name = "emp_username",length = 10,unique = true)
    String username;

    @Column(name = "emp_phone",length = 11,unique = true)
    String password;

    @Column(name = "emp_phone",length = 11,unique = true)
    String phone;

    @Column(name = "emp_indetity",length = 18,unique = true)
    String identity;

    @Column(name = "emp_gender")
    Integer gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "emp_birthday")
    Date birthday;

    @Column(name = "emp_address")
    String address;

    @Column(name = "emp_status")
    Boolean status;

    @Temporal(TemporalType.DATE)
    @Column(name = "emp_entryDate")
    Date entryDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "emp_turnoverDate")
    Date turnoverDATE;

    @ManyToOne(fetch = FetchType.LAZY ,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "dept_id")
    Dept dept;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "level_id")
    EmployeeLevel employeelevel;

}
