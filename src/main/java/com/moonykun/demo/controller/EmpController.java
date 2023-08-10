package com.moonykun.demo.controller;


import com.moonykun.demo.domain.Dept;
import com.moonykun.demo.domain.Emp;
import com.moonykun.demo.service.EmpService;
import com.moonykun.demo.vo.EmpQuery;
import com.moonykun.demo.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @ResponseBody
    @PostMapping("")
    public Result<Object> addEmp(Emp emp) {
        empService.addEmp(emp);
        return Result.success("新增成功");
    }

    @GetMapping("/add")
    public String getAddEmpPage(Model model) {
        List<Dept> deptList = empService.getDept();
        model.addAttribute("deptList",deptList);
        return "emp/empAdd";
    }

    @DeleteMapping("/{ids}")
    @ResponseBody
    public Result<Object> deleteEmp(@PathVariable("ids") String ids){
        empService.deleteEmp(ids);
        return Result.success("删除成功");
    }

    @GetMapping("/{id}")
    public String getEmpById(@PathVariable("id") Long id,Model model) {
        Emp emp = empService.getEmpById(id);
        model.addAttribute("emp",emp);
        model.addAttribute("deptList",empService.getDept());
        return "emp/empEdit";
    }

    @PutMapping("/update")
    @ResponseBody
    public Result<Object> updateEmp(Emp emp) {
        empService.updateEmp(emp);
        return Result.success("修改成功");
    }
}
