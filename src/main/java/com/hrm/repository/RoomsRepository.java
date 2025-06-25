package com.hrm.repository;

import com.hrm.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomsRepository extends JpaRepository<Rooms,Long> {
    List<Rooms> findByPropertyId(long propertyId);
}
