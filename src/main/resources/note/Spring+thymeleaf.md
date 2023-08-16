# 项目管理

## 登录功能

### 密码加密

```java
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
```

工具类：BCryptPasswordEncoder

步骤：是否存在-->判断密码是否正确



## 登录页面

登录页面主要通过修改layui 以及layuimini中的页面实现

需要将从layuimini中复制的页面中的路径进行修改

具体例子如下：

```html
<script th:src="@{/webjars/jquery/3.5.1/jquery.min.js}" charset="utf-8"></script>
<script th:src="@{/layui/layui.js}" charset="utf-8"></script>
<script th:src="@{/lib/jq-module/jquery.particleground.min.js}" charset="utf-8"></script>
```



## 配置上下文context

// 防止改变springboot配置文件后路径需要进一步修改

SpringBoot配置

```yml
  servlet:
    context-path: /moonykun/
```

修改

```json
var ctxPath = [[@{/}]];
        alert(ctxPath);
```

### 登录拦截器

作用：阻挡非法访问请求

实现：SpringMVC 拦截器

实现拦截器：

```java
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
```

注册拦截器：

```java
public class MyWebConfig implements WebMvcConfigurer {

    @Autowired
    @Qualifier(value = "loginInterceptor")
    HandlerInterceptor handlerInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(handlerInterceptor);
        registration.addPathPatterns("/**");
        registration.excludePathPatterns(
                "/login",
                "/captcha",
                "/user/login",
                "/user/logout",
                "/layui/**",
                "/lib/**",
                "/webjars/**",
                "/api/**",
                "/css/**",
                "/js/**",
                "/images/**",
                "/index");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
```

### 后台首页布局

这边遇到一个非常难以解决的问题，及版本问题，在使用对应的模板时，因注意改模板已经将对应的版本号建如自己的文件夹内，使用文件夹内对应的版本即可。

## 查询页面

第一步：

```HTML
<div class="div-content">
    <fieldset class="table-search-fieldset">
        <legend>搜索条件</legend>
        <div style="margin: 10px 10px 10px 10px">
            <form class="layui-form layui-form-pane" action="">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">用户姓名</label>
                        <div class="layui-input-inline">
                            <input type="text" name="username" autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">生日</label>
                        <div class="layui-input-inline" style="width: 120px;">
                            <input type="text" name="startDate" placeholder="开始日期" autocomplete="off"
                                   class="layui-input" id="emp-start-date">
                        </div>

                        <div class="layui-form-mid">-</div>

                        <div class="layui-input-inline" style="width: 120px;">
                            <input type="text" name="endDate" placeholder="结束日期" autocomplete="off"
                                   class="layui-input" id="emp-end-date">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <button  type="submit" class="layui-btn layui-btn-primary" lay-submit=""
                                lay-filter="data-search-btn"><i class="layui-icon"></i> 搜 索
                        </button>
                    </div>

                </div>
            </form>
        </div>
    </fieldset>
    <table class="layui-hide" id="ID-table-demo-page" lay-filter="table-emp-filter"></table>
```

第二步

绑定搜索按钮

### 多条删除实现

