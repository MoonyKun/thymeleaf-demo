package com.moonykun.demo.controller;


import com.moonykun.demo.domain.Dept;
import com.moonykun.demo.domain.Emp;
import com.moonykun.demo.service.EmpService;
import com.moonykun.demo.vo.EmpQuery;
import com.moonykun.demo.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author Moonykun
 */
@RestController
@RequestMapping("/emp")
public class EmpController {
    @Autowired
    EmpService empService;

    @GetMapping("/list")
    public Result<Object> listEmp(EmpQuery empQuery) {
        List<Emp> emps = empService.listEmp(empQuery);
        Long count =empService.countEmp(empQuery);
        return Result.success(emps,count);
    }

    @PostMapping("")
    public Result<Object> addEmp(Emp emp) {
        empService.addEmp(emp);
        return Result.success("新增成功");
    }

    @GetMapping("/add")
    public Result<Object> getAddEmpPage() {
        List<Dept> deptList = empService.getDept();
        return Result.success(deptList);
    }

    @DeleteMapping("/{ids}")
    public Result<Object> deleteEmp(@PathVariable("ids") String ids){
        empService.deleteEmp(ids);
        return Result.success("删除成功");
    }

    @GetMapping("/{id}")
    public Result<Object> getEmpById(@PathVariable("id") Integer id, Model model) {
        Emp emp = empService.getEmpById(id);
        List<Dept> dept = empService.getDept();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("emp",emp);
        hashMap.put("dept",dept);
        return Result.success(hashMap);
    }

    @PutMapping("/update")
    public Result<Object> updateEmp(Emp emp) {
        empService.updateEmp(emp);
        return Result.success("修改成功");
    }

    @GetMapping("/getAllEmp")
    public Result<Object> getAllEmp() {
        List<Emp> emps = empService.getAllEmp();
        return Result.success(emps);
    }
}
