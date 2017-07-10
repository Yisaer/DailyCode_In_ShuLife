package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Yisa on 2017/7/9.
 */

@Entity
@Table(name = "m_dept")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept {

    @Id
    @GeneratedValue
    @Column(name = "dept_id")
    Integer id;

    @Column(name = "dept_name",length = 10)
    String name ;

    @OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL,mappedBy = "dept")
    List<Employee> employees;
}
