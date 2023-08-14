package com.moonykun.demo.Repository;

import com.moonykun.demo.domain.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmpRepository extends JpaRepository<Emp, Integer>, JpaSpecificationExecutor<Emp> {
    // 这个是通过spring data拼接关键字进行的操作
    @Modifying
    @Transactional
    @Query("delete from Emp s where s.empId in (?1)")
    void deleteBatch(List<Integer> ids);
    void deleteEmpsByEmpIdIn(List<Integer> ids);
}