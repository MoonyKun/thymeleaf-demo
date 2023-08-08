package com.moonykun.demo.controller;


import com.moonykun.demo.domain.Emp;
import com.moonykun.demo.service.EmpService;
import com.moonykun.demo.vo.EmpQuery;
import com.moonykun.demo.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/emp")
public class EmpController {
    @Autowired
    EmpService empService;

    @GetMapping("")
    public String emp() {
        return "emp/empList";
    }
    @GetMapping("/list")
    @ResponseBody
    public Result<Object> listEmp(EmpQuery empQuery) {
        List<Emp> emps = empService.listEmp(empQuery);
        Long count =empService.countEmp(empQuery);
        return Result.success(emps,count);
    }
}
