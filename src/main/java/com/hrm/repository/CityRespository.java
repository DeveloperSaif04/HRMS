package com.hrm.repository;

import com.hrm.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRespository extends JpaRepository<City,Long> {
    City findByName(String name);

}
