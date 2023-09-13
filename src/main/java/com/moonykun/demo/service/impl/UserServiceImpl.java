package com.moonykun.demo.service.impl;

import com.moonykun.demo.Repository.UserRepository;
import com.moonykun.demo.domain.User;
import com.moonykun.demo.service.UserService;
import com.moonykun.demo.vo.UserQuery;
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
import java.util.ArrayList;
import java.util.List;


/**
 * @author Moonykun
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserRepository userRepository;

    @Override
    public User login(User user) {
        return userRepository.getUserByUsername(user.getUsername());
    }

    @Override
    public List<User> listUser(UserQuery userQuery) {
        Specification<User> userSpecification = (root, criteriaQuery, criteriaBuilder) -> getPredicate(root, criteriaBuilder, userQuery);
        Pageable pageable = PageRequest.of(userQuery.getStart(), userQuery.getLimit());
        Page<User> all = userRepository.findAll(userSpecification, pageable);
        return all.getContent();
    }

    @Override
    public Long countUser(UserQuery userQuery) {
        Specification<User> userSpecification = (root, criteriaQuery, criteriaBuilder) -> getPredicate(root, criteriaBuilder, userQuery);
        long count = userRepository.count(userSpecification);
        return count;
    }

    @Override
    public void deleteUserByIds(String ids) {

        // 接收包含empId的字符串，并将它分割成字符串数组
        String[] userList = ids.split(",");
        // 将字符串数组转为List<Integer> 类型
        List<Integer> LString = new ArrayList<Integer>();
        for (String str : userList) {
            LString.add(new Integer(str));
        }
        userRepository.deleteBatch(LString);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    private Predicate getPredicate(Root<User> root, CriteriaBuilder criteriaBuilder, UserQuery userQuery) {
        List<Predicate> predicates = new ArrayList<>();
        if (userQuery.getName() != null && !"".equals(userQuery.getName())) {
            Path<Object> username = root.get("username");
            Predicate name = criteriaBuilder.like(username.as(String.class), "%" + userQuery.getName() + "%");
            predicates.add(name);
        }
        if (userQuery.getChName() != null && !"".equals(userQuery.getChName())) {
            Path<Object> chName = root.get("chName");
            Predicate cname = criteriaBuilder.like(chName.as(String.class), '%' + userQuery.getChName() + "%");
            predicates.add(cname);
        }
        Predicate[] predicates1 = predicates.toArray(new Predicate[0]);
        return criteriaBuilder.and(predicates1);
    }
}
