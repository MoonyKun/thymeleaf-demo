package com.moonykun.demo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Moonykun
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object userInfo = request.getSession().getAttribute("userInfo");

        if (userInfo == null) {
            // 重定向
            response.sendRedirect(request.getContextPath() + "login");
            // 返回false
            log.debug("无效登录请求："+request.getSession());
            return false;
        }
        log.info("有效登录请求："+request.getSession());
        return true;
    }
}
