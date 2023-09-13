package com.moonykun.demo.config;

/**
 * @author Moonykun
 */
//@Configuration
//public class MyWebConfig implements WebMvcConfigurer {
//
//    @Autowired
//    @Qualifier(value = "loginInterceptor")
//    HandlerInterceptor handlerInterceptor;
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration registration = registry.addInterceptor(handlerInterceptor);
//        registration.addPathPatterns("/**");
//        registration.excludePathPatterns(
//                "/login",
//                "/captcha",
//                "/user/login",
//                "/user/logout",
//                "/layui/**",
//                "/lib/**",
//                "/webjars/**",
//                "/api/**",
//                "/css/**",
//                "/js/**",
//                "/images/**",
//                "/index",
//                "/null/swagger-resources",
//                "/favicon.ico");
//        WebMvcConfigurer.super.addInterceptors(registry);
//    }
//}
