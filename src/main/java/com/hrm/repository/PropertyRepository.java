package com.hrm.repository;

import com.hrm.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PropertyRepository extends JpaRepository<Property,Long>, JpaSpecificationExecutor<Property> {

}
