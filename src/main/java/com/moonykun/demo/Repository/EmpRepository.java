package com.moonykun.demo.Repository;

import com.moonykun.demo.domain.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Moonykun
 */
public interface EmpRepository extends JpaRepository<Emp, Integer>, JpaSpecificationExecutor<Emp> {
    // 这个是通过spring data拼接关键字进行的操作
    @Modifying
    @Transactional
    @Query(value = "delete from Emp s where s.empId in (?1)")
    void deleteBatch(List<Integer> ids);
}