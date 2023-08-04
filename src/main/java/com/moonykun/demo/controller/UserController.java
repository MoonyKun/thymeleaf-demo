package com.moonykun.demo.controller;

import com.moonykun.demo.domain.User;
import com.moonykun.demo.service.UserService;
import com.moonykun.demo.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


/**
 * @author Moonykun
 */
@Controller
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Result login(User param, HttpSession httpSession) {
        User user = userService.login(param);
      // 首先判断是否找到user
        if (user != null) {
            // 初始化加密工具类
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            //比对密码 注意参数的位置第一位是原始数据，第二位是加密后的数据
            boolean matches = bCryptPasswordEncoder.matches(param.getPassword(),user.getPassword());
            if (matches) {
                user.setPassword(null);
                httpSession.setAttribute("userInfo",user);
                return Result.success();
            }
        }
        return Result.fail("用户名或密码错误");
    }
}
