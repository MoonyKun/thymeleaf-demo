package com.moonykun.demo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Moonykun
 */
@Data
public class Page implements Serializable {
    private Integer page;

    private Integer limit;

    public Integer getStart(){
        return (page - 1 ) * limit;
    }
}
