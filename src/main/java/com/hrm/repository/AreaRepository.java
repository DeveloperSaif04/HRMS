package com.hrm.repository;

import com.hrm.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaRepository extends JpaRepository<Area,Long> {

    Area  findByName(String name);

}
