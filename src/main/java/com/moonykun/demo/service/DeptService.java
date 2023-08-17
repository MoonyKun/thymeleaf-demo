package com.moonykun.demo.service;

import com.moonykun.demo.domain.Dept;
import com.moonykun.demo.vo.DeptQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeptService {

    List<Dept> listDept(DeptQuery deptQuery);

    Long countDeptList(DeptQuery deptQuery);
}
