package com.moonykun.demo.mapper;

import com.moonykun.demo.domain.Dept;

/**
* @author Moonykun
* @description 针对表【dept】的数据库操作Mapper
* @createDate 2023-08-03 15:28:22
* @Entity com.moonykun.demo.domain.Dept
*/
public interface DeptMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);

}
