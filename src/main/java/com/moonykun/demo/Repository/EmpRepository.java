package com.moonykun.demo.Repository;

import com.moonykun.demo.domain.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface EmpRepository extends JpaRepository<Emp, Integer>, JpaSpecificationExecutor<Emp> {

    @Query(value="select radar_indexcode from  t_station_device_config",nativeQuery = true)
    public void updateEmp(Emp emp);
}