package com.hrm.repository;

import com.hrm.entity.AvailableRooms;
import com.hrm.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AvailableRoomsRepository extends JpaRepository<AvailableRooms,Long> {

//    @Query("SELECT a FROM AvailableRooms a WHERE a.rooms = :rooms AND a.availableDateStart <= :endDate AND a.availableDateEnd >= :startDate")
@Query("SELECT a FROM AvailableRooms a WHERE a.rooms = :rooms AND a.availableDate BETWEEN :startDate AND :endDate")
List<AvailableRooms> findAvailableRooms(
        @Param("rooms") Rooms rooms,
        @Param("startDate") LocalDate startDate,
        @Param("endDate") LocalDate endDate);


}
