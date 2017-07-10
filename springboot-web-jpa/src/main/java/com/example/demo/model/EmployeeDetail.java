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
@Table(name = "m_employee_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetail {

    @Id
    @Column(name = "emp_id")
    Long id;

    /**
     * 上次登录时间
     */
    @Column(name = "emp_last_login_time")
    @Temporal(TemporalType.TIMESTAMP)
    Date loginTime;

    /**
     * 上次登录IP
     */
    @Column(name = "emp_laste_login_ip",length = 15)
    String loginIp;

    /**
     *  上次登录IP
     */
    @Column(name = "emp_login_count")
    Integer loginCount;

}
