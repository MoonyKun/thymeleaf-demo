package com.moonykun.demo.service.impl;

import com.moonykun.demo.domain.User;
import com.moonykun.demo.mapper.UserMapper;
import com.moonykun.demo.service.UserService;
import com.moonykun.demo.vo.UserQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author Moonykun
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;
    @Override
    public User login(User user) {
        return userMapper.getUser(user);
    }

    @Override
    public List<User> listUser(UserQuery userQuery) {
        return userMapper.getAllByUSerQuery(userQuery);
    }

    @Override
    public Long countUser(UserQuery userQuery) {
        return userMapper.countByUserQuery(userQuery);
    }

    @Override
    public void deleteUserByIds(String ids) {
        userMapper.deleteByIds(ids);
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }
}
