package com.moonykun.demo.Repository;

import com.moonykun.demo.domain.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Moonykun
 */
public interface DeptRepository extends JpaRepository<Dept, Integer>, JpaSpecificationExecutor<Dept> {

    @Modifying
    @Transactional
    @Query(value = "delete from Dept s where s.deptId in (?1)")
    void deleteBatch(List<Integer> ids);
}