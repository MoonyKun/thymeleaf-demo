package com.moonykun.demo.Repository;

import com.moonykun.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    User getUserByUsername(String username);

    @Modifying
    @Transactional
    @Query("delete from User s where s.id in (?1)")
    void deleteBatch(List<Integer> ids);
}