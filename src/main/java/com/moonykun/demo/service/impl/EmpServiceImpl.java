package com.moonykun.demo.service.impl;

import com.moonykun.demo.Repository.DeptRepository;
import com.moonykun.demo.Repository.EmpRepository;
import com.moonykun.demo.domain.Dept;
import com.moonykun.demo.domain.Emp;
import com.moonykun.demo.service.EmpService;
import com.moonykun.demo.vo.EmpQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moonykun
 */
@Service
public class EmpServiceImpl implements EmpService {
    @Resource
    EmpRepository empRepository;

    @Resource
    DeptRepository deptRepository;

    @Override
    public List<Emp> listEmp(EmpQuery empQuery) {
        Specification<Emp> empSpecification = (root, criteriaQuery, criteriaBuilder) -> getEmpPredicate(root, criteriaBuilder, empQuery);
        Pageable pageable = PageRequest.of(empQuery.getStart(), empQuery.getLimit());
        Page<Emp> all = empRepository.findAll(empSpecification, pageable);
        return all.getContent();
    }

    @Override
    public Long countEmp(EmpQuery empQuery) {
        Specification<Emp> empSpecification = (root, criteriaQuery, criteriaBuilder) -> getEmpPredicate(root, criteriaBuilder, empQuery);
        return empRepository.count(empSpecification);
    }

    private Predicate getEmpPredicate(Root<Emp> root, CriteriaBuilder criteriaBuilder, EmpQuery empQuery) {
        List<Predicate> predicates = new ArrayList<>();
        if (empQuery.getName() != null && !"".equals(empQuery.getName())) {
            Path<Object> name = root.get("name");
            Predicate like = criteriaBuilder.like(name.as(String.class), "%" + empQuery.getName() + "%");
            predicates.add(like);
        }
        if (empQuery.getStartDate() != null) {
            Path<Object> birthday = root.get("birthday");
            Predicate startDate = criteriaBuilder.greaterThanOrEqualTo(birthday.as(Date.class), empQuery.getStartDate());
            predicates.add(startDate);
        }
        if (empQuery.getEndDate() != null) {
            Path<Object> birthday = root.get("birthday");
            Predicate endDate = criteriaBuilder.lessThanOrEqualTo(birthday.as(Date.class), empQuery.getEndDate());
            predicates.add(endDate);
        }
        Predicate[] p = predicates.toArray(new Predicate[0]);
        return criteriaBuilder.and(p);
    }

    @Override
    public void addEmp(Emp emp) {
        empRepository.saveAndFlush(emp);
    }

    @Override
    public List<Dept> getDept() {
        return deptRepository.findAll();
    }

    @Override
    public void deleteEmp(String ids) {
        // 接收包含empId的字符串，并将它分割成字符串数组
        String[] empList = ids.split(",");
        // 将字符串数组转为List<Integer> 类型
        List<Integer> LString = new ArrayList<Integer>();
        for (String str : empList) {
            LString.add(new Integer(str));
        }
        empRepository.deleteBatch(LString);
    }

    @Override
    public Emp getEmpById(Integer id) {
        return empRepository.findById(id).get();
    }

    @Override
    public void updateEmp(Emp emp) {
        empRepository.save(emp);
    }

    @Override
    public List<Emp> getAllEmp() {
        return empRepository.findAll();
    }
}