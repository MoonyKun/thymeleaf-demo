package com.moonykun.demo.controller;

import com.moonykun.demo.domain.User;
import com.moonykun.demo.service.UserService;
import com.moonykun.demo.vo.Result;
import com.moonykun.demo.vo.UserQuery;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * @author Moonykun
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Result<Object> login(User param, @RequestParam("captcha") String verCode, HttpServletRequest request, HttpSession httpSession) {
        User user = userService.login(param);
        // 校验验证码
        if (!CaptchaUtil.ver(verCode, request)) {
            CaptchaUtil.clear(request);  // 清除session中的验证码并非刷新验证码
            return Result.fail("验证码错误");
        }
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

    @GetMapping("/list")
    public Result<Object> listUser(UserQuery userQuery) {
        List<User> userList = userService.listUser(userQuery);
        Long userCount = userService.countUser(userQuery);
        return Result.success(userList,userCount);
    }

    @DeleteMapping("/delete/{ids}")
    public Result<Object> deleteUser(@PathVariable("ids") String ids) {
        userService.deleteUserByIds(ids);
        return Result.success("删除成功");
    }
    @GetMapping("/{id}")
    public Result<Object> getUser(@PathVariable("id") Integer id, Model model) {
        User user = userService.getUserById(id);
        return Result.success(user);
    }
    @PutMapping("/update")
    public Result<Object> updateUser(User user) {
        userService.updateUser(user);
        return Result.success("修改成功");
    }

    @GetMapping("/listAllUsers")
    public Result<Object> listAllUsers() {
        List<User> userList = userService.listAllUsers();
        return Result.success(userList);
    }
}
