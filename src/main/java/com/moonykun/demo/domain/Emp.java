package com.moonykun.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@Entity
@Table(name = "emp")
public class Emp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "emp_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empId;

    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private String sex;

    @Column(name = "age")
    private Integer age;

    @Column(name = "sal")
    private BigDecimal sal;

    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @Column(name = "address")
    private String address;

    @Column(name = "dept_id")
    private Integer deptId;

    @ManyToOne
    @JoinColumn(name = "dept_id",referencedColumnName = "dept_id",insertable = false, updatable = false)
    private Dept dept;
}
