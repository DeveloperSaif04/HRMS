package com.hrm.repository;

import com.hrm.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT b FROM Booking b WHERE b.room.id = :roomId AND b.fromDate <= :toDate AND b.toDate >= :fromDate")
    List<Booking> findOverlappingBookings(@Param("roomId") Long roomId, @Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);
}
