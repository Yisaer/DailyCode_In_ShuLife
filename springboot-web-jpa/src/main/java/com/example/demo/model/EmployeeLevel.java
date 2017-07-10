package com.example.demo.model;

/**
 * Created by Yisa on 2017/7/9.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 职位
 */

@Entity
@Table(name="m_employee_level")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeLevel {

    @Id
    @GeneratedValue
    @Column(name = "level_id")
    Integer id;

    @Column(name = "level_name")
    String name;
}
