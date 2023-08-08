package com.moonykun.demo.service.impl;

import com.moonykun.demo.domain.Emp;
import com.moonykun.demo.mapper.EmpMapper;
import com.moonykun.demo.service.EmpService;
import com.moonykun.demo.vo.EmpQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Moonykun
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Resource
    EmpMapper empMapper;
    @Override
    public List<Emp> listEmp(EmpQuery empQuery) {
        return empMapper.listAllByEmpQuery(empQuery);
    }

    @Override
    public Long countEmp(EmpQuery empQuery) {
        return empMapper.countByEmpQuery(empQuery);
    }
}
