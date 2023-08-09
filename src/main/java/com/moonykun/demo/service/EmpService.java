package com.moonykun.demo.service;

import com.moonykun.demo.domain.Emp;
import com.moonykun.demo.vo.EmpQuery;

import java.util.List;

public interface EmpService {
    List<Emp> listEmp(EmpQuery empQuery);

    Long countEmp(EmpQuery empQuery);

    void addEmp(Emp emp);
}
