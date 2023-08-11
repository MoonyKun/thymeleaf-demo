package com.moonykun.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "dept")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "dept_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deptId;

    @Column(name = "dept_name")
    private String deptName;
}
