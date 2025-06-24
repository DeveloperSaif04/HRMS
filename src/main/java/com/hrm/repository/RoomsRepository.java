package com.hrm.repository;

import com.hrm.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomsRepository extends JpaRepository<Rooms,Long> {
}
