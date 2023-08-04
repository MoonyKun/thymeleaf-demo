package com.moonykun.demo.service.impl;

import com.moonykun.demo.domain.User;
import com.moonykun.demo.mapper.UserMapper;
import com.moonykun.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


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
}
