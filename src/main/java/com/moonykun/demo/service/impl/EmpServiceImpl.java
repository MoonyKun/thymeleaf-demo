package com.moonykun.demo.service.impl;

import com.moonykun.demo.domain.Dept;
import com.moonykun.demo.domain.Emp;
import com.moonykun.demo.mapper.DeptMapper;
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
    @Resource
    DeptMapper deptMapper;
    @Override
    public List<Emp> listEmp(EmpQuery empQuery) {
        return empMapper.listAllByEmpQuery(empQuery);
    }

    @Override
    public Long countEmp(EmpQuery empQuery) {
        return empMapper.countByEmpQuery(empQuery);
    }

    @Override
    public void addEmp(Emp emp) {
        empMapper.insert(emp);
    }

    @Override
    public List<Dept> getDept() {
        return deptMapper.getAll();
    }

    @Override
    public void deleteEmp(String ids) {
        empMapper.deleteEmp(ids);
    }

    @Override
    public Emp getEmpById(Long id) {
        return empMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateEmp(Emp emp) {
        empMapper.updateByPrimaryKeySelective(emp);
    }
}
