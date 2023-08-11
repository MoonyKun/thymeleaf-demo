package com.moonykun.demo.service;

import com.moonykun.demo.mapper.DeptMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DeptService {
    @Resource
    DeptMapper deptMapper;
}
