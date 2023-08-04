package com.moonykun.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Moonykun
 */
@Controller
public class CommonController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/index")
   public String index() {
        return "index";
   }
}
