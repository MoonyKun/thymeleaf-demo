package com.moonykun.demo.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
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

    private static final long serialVersionUID = 1L;
}