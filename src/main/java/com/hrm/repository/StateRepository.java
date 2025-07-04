package com.hrm.repository;

import com.hrm.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State,Long> {

     State findByName(String name);
}
