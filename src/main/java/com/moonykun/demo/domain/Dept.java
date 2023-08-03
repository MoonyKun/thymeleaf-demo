package com.moonykun.demo.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName dept
 */
@Data
public class Dept implements Serializable {
    /**
     * 
     */
    private Integer deptId;

    /**
     * 
     */
    private String deptName;

    private static final long serialVersionUID = 1L;
}