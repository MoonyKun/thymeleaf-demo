package com.moonykun.demo.controller;

import com.moonykun.demo.domain.Dept;
import com.moonykun.demo.service.DeptService;
import com.moonykun.demo.vo.DeptQuery;
import com.moonykun.demo.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Moonykun
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    DeptService deptService;

    @GetMapping("/list")
    public Result<Object> listDept(DeptQuery deptQuery) {
        Long count = deptService.countDeptList(deptQuery);
        List<Dept> deptList = deptService.listDept(deptQuery);
        return Result.success(deptList,count);
    }

    @GetMapping("{ids}")
    public Result<Object> deleteDept(@PathVariable("ids") String ids) {
        deptService.deleteDept(ids);
        return Result.success("删除成功");
    }
}
