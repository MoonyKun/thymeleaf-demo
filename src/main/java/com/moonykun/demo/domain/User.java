package com.moonykun.demo.domain;

import lombok.Data;


import java.io.Serializable;

/**
 * 
 * @author Moonykun
 * @TableName user
 */
@Data
public class User implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String chName;


    private static final long serialVersionUID = 1L;
}