package com.moonykun.demo.mapper;

import com.moonykun.demo.domain.User;

/**
* @author Moonykun
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-08-03 15:29:04
* @Entity com.moonykun.demo.domain.User
*/
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User getUser(User user);

}
