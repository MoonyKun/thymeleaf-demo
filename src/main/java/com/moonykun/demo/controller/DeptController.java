package com.moonykun.demo.controller;

import com.moonykun.demo.domain.Dept;
import com.moonykun.demo.service.DeptService;
import com.moonykun.demo.vo.DeptQuery;
import com.moonykun.demo.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Moonykun
 */
@Controller
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    DeptService deptService;
    @GetMapping("")
    public String getDeptList() {
        return "dept/deptList";
    }
    @GetMapping("/list")
    @ResponseBody
    public Result<Object> listDept(DeptQuery deptQuery) {
        Long count = deptService.countDeptList(deptQuery);
        List<Dept> deptList = deptService.listDept(deptQuery);
        return Result.success(deptList,count);
    }

    @GetMapping("{ids}")
    public String deleteDept( @PathVariable("ids") String ids) {
        deptService.deleteDept(ids);
        return null;
    }
}
