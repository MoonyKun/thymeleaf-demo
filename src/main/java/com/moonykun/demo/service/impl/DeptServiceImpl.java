package com.moonykun.demo.service.impl;

import com.moonykun.demo.Repository.DeptRepository;
import com.moonykun.demo.domain.Dept;
import com.moonykun.demo.service.DeptService;
import com.moonykun.demo.vo.DeptQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moonykun
 */

@Service
public class DeptServiceImpl implements DeptService {
    @Resource
    DeptRepository deptRepository;
    @Override
    public List<Dept> listDept(DeptQuery deptQuery) {
        Specification<Dept> deptSpecification = (root, criteriaQuery, criteriaBuilder) -> getPredicate(root, criteriaBuilder, deptQuery);
        Pageable pageable = PageRequest.of(deptQuery.getStart(),deptQuery.getLimit());
        Page<Dept> all = deptRepository.findAll(deptSpecification, pageable);
        return all.getContent();
    }

    @Override
    public Long countDeptList(DeptQuery deptQuery) {
        Specification<Dept> deptSpecification = (root, criteriaQuery, criteriaBuilder) -> getPredicate(root, criteriaBuilder, deptQuery);
        deptRepository.count(deptSpecification);
        return null;
    }

    @Override
    public void deleteDept(String ids) {
        // 接收包含empId的字符串，并将它分割成字符串数组
        String[] deptList = ids.split(",");
        // 将字符串数组转为List<Integer> 类型
        List<Integer> lString = new ArrayList<Integer>();
        for (String str : deptList) {
            lString.add(new Integer(str));
        }
        deptRepository.deleteBatch(lString);
    }

    private Predicate getPredicate(Root<Dept> root, CriteriaBuilder criteriaBuilder, DeptQuery deptQuery) {
        List<Predicate> predicates = new ArrayList<>();
        if (deptQuery.getDeptId() != null && !"".equals(deptQuery.getDeptId())) {
            Path<Object> deptId = root.get("deptId");
            Predicate equal = criteriaBuilder.equal(deptId.as(Integer.class), deptQuery.getDeptId());
            predicates.add(equal);
        }
        if (deptQuery.getDeptName() != null && !"".equals(deptQuery.getDeptName())) {
            Path<Object> deptName = root.get("deptName");
            Predicate like = criteriaBuilder.like(deptName.as(String.class), "%" + deptQuery.getDeptName() + "%");
            predicates.add(like);
        }
        Predicate[] predicates1 = predicates.toArray(new Predicate[0]);
        return criteriaBuilder.and(predicates1);
    }
}
