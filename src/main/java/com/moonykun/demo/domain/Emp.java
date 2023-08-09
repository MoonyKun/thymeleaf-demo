package com.moonykun.demo.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Moonykun
 * @TableName emp
 */
@Data
public class Emp implements Serializable {
    /**
     * 
     */
    private Integer empId;

    /**
     * 
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String sex;

    /**
     * 
     */
    private Integer age;

    /**
     * 
     */
    private Long sal;

    /**
     * 
     */
    private String address;

    /**
     * 
     */
    private Integer deptId;

    private Dept dept;

    private static final long serialVersionUID = 1L;
}