package com.moonykun.demo.interceptor;

/**
 * @author Moonykun
 */
//@Slf4j
//@Component
//public class LoginInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        Object userInfo = request.getSession().getAttribute("userInfo");
//
//        if (userInfo == null) {
//            // 重定向
//            response.sendRedirect(request.getContextPath() + "/login");
//            // 返回false
//            log.debug("无效请求："+request.getRequestURI());
//            return false;
//        }
//        log.info("有效请求："+request.getRequestURI());
//        return true;
//    }
//}
