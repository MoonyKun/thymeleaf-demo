package com.moonykun.demo.service;

import com.moonykun.demo.domain.User;
import com.moonykun.demo.vo.UserQuery;

import java.util.List;

/**
 * @author Moonykun
 */
public interface UserService {
    public User login(User user);

    List<User> listUser(UserQuery userQuery);

    Long countUser(UserQuery userQuery);

    void deleteUserByIds(String ids);

    User getUserById(Long id);

    void updateUser(User user);
}
