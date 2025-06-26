package com.hrm.service;

import com.hrm.dto.BookingDto;
import com.hrm.entity.Booking;
import com.hrm.entity.Property;
import com.hrm.entity.Rooms;
import com.hrm.exception.BookingOverlapException;
import com.hrm.repository.BookingRepository;
import com.hrm.repository.PropertyRepository;
import com.hrm.repository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RoomsRepository roomsRepository;
    @Autowired
    private PropertyRepository propertyRepository;

    public Booking createBooking(BookingDto dto) {
        Rooms room = roomsRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        Property property = propertyRepository.findById(dto.getPropertyId())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        if (!room.getProperty().getId().equals(property.getId())) {
            throw new RuntimeException("Room does not belong to hotel");
        }

        List<Booking> overlaps = bookingRepository.findOverlappingBookings(dto.getRoomId(), dto.getFromDate(), dto.getToDate());
        if (!overlaps.isEmpty()) {
            throw new BookingOverlapException("Room already booked for selected dates");
        }
        Booking booking = new Booking();
        booking.setUserId(dto.getUserId());
        booking.setRoom(room);
        booking.setProperty(property);
        booking.setFromDate(dto.getFromDate());
        booking.setToDate(dto.getToDate());
        booking.setCreatedAt(LocalDate.now());
        return bookingRepository.save(booking);
    }
}

