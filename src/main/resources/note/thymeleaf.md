# Thymeleaf

##  参考资料

- [Thymeleaf 教程 | 範宗雲 (fanlychie.github.io)](https://fanlychie.github.io/post/thymeleaf.html)



## SpringBooot整合thymeleaf

1.   控制器

   ```java
   @Controller
   public class WelcomeController {
       
       @GetMapping("/")
       public String welcome(ModelMap model) {
           model.put("message", "Hello Thymeleaf!");
           return "index";
       }
       
   }
   ```

   解释： GetMapping内为访问路径、return语句内设定跳转的页面

2. 模板文件 

   ```HTML
   <!DOCTYPE HTML>
   <html xmlns:th="http://www.thymeleaf.org">
   <head>
       <title>首页</title>
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
       <link rel="stylesheet" type="text/css" th:href="@{/css/main.css}">
   </head>
   <body>
       <h1 th:text="${message}"></h1>
   </body>
   </html>
   ```

   Spring Boot 对 Thymeleaf 模板引擎提供了自动配置的支持，我们只需遵循约定，在`/src/main/resources/templates/`目录创建相应的页面模板文件（`*.html`）即可

3. 静态文件

   Spring Boot 默认将静态资源文件映射到类路径下的目录包括

   - /META-INF/resources/
   - /resources/
   - /static/
   - /public/

   因此我们可以将 css、js、images 等静态资源文件放在`/src/main/resources/static/`目录下。

   ```css
   body {
       padding: 0;
       color: #444;
       width: 280px;
       margin: 100px auto;
       font-family: SimSun;
       background-color: #FBFBFB;
       text-shadow: rgba(50,50,50,0.3) 2px 2px 3px;
   }
   ```

4. 模板文件和静态资源文件的缓存问题

   当修改 css、js 等静态资源文件的内容或模板文件的内容时，刷新客户端浏览器，发现内容还是老的，说明 Spring Boot 内置的 Servelt 容器并没有实时重新加载修改过的文件内容。你只能在每次修改静态资源文件时，虽然不需要重启服务，但是你要重新编译一次，IntelliJ IDEA 中按一次 Ctrl + F9 即可。
   实现热加载（live reload）

附：IDEA添加自定义文件过程：打开idea 文件和代码模板编辑即可



