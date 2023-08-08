package com.moonykun.demo.mapper;

import com.moonykun.demo.domain.Emp;
import com.moonykun.demo.vo.EmpQuery;

import java.util.List;

/**
* @author Moonykun
* @description 针对表【emp】的数据库操作Mapper
* @createDate 2023-08-03 15:28:55
* @Entity com.moonykun.demo.domain.Emp
*/
public interface EmpMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Emp record);

    int insertSelective(Emp record);

    Emp selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Emp record);

    int updateByPrimaryKey(Emp record);

    List<Emp> listAllByEmpQuery(EmpQuery empQuery);

    Long countByEmpQuery(EmpQuery empQuery);
}
