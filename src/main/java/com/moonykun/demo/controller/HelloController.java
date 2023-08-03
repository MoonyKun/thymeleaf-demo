package com.moonykun.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Moonykun
 */
@Controller
public class HelloController {

    @GetMapping("/")
    public String hello(ModelMap model) {
        model.put("hello", "Hello Thymeleaf!");
        return "hello";
    }
}
