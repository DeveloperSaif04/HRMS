package com.hrm.controller;

import com.hrm.dto.AvailableRoomsDto;
import com.hrm.dto.BookingDto;
import com.hrm.dto.PropertyDto;
import com.hrm.dto.ResponseDto;
import com.hrm.entity.Booking;
import com.hrm.entity.EmailRequest;
import com.hrm.entity.Property;
import com.hrm.exception.WrongWayDateEntryException;
import com.hrm.service.BookingService;
import com.hrm.service.EmailService;
import com.hrm.service.PropertyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/property")
@Validated
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/create")
    public ResponseEntity<Property> create(@RequestBody PropertyDto propertyDto){
      Property savedProperty = propertyService.createProperty(propertyDto);
       return new ResponseEntity<>(savedProperty,HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Property>> searchProperty(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            //for pagination and sorting
             @RequestParam(defaultValue = "0") int page,
             @RequestParam(defaultValue = "2") int size,
             @RequestParam(defaultValue = "id") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Property> result = propertyService.propertySearch(city, rating, minPrice, maxPrice,pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/AllProperty")
    public ResponseEntity<Page<Property>> allProperty(
            @Valid
            @RequestParam(defaultValue = "0")@Min(0) int pageNo,
            @RequestParam(defaultValue = "2")@Min(1) int pageSize,
            @RequestParam(defaultValue = "name") String sortBy

    ){
         Page<Property> listOfProperty = propertyService.listOfProperty(pageNo,pageSize,sortBy);
        return  new ResponseEntity<>(listOfProperty, HttpStatus.OK);
    }
// url-> GET /roomAvailable/1?startDate=2025-06-15&endDate=2025-06-18
    @PostMapping("/roomAvailable/{hotelId}")
    public ResponseEntity<?> roomAvl(
            @PathVariable long hotelId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        if(startDate.isAfter((endDate))){
            throw new WrongWayDateEntryException("Start date must be before or equal to end date");
        }

        List<AvailableRoomsDto> availableRooms = propertyService.roomAvailable(hotelId, startDate, endDate);
        return new ResponseEntity<>(availableRooms, HttpStatus.OK);
    }

        //handler method to perform booking
        @PostMapping("/bookings")
        public ResponseEntity<ResponseDto> bookRoom(@RequestBody BookingDto dto) {
            Booking  booking = bookingService.createBooking(dto);
            ResponseDto response = new ResponseDto();
            response.setStatusCode("200");
            response.setMessage("Booking successful");
            response.setResponseData(Map.of("room booked successfully with booking id", booking.getId()));
            //send booking email confirmation
            emailService.sendEmail(
                    new EmailRequest(
                            "praveenagrawal.work@gmail.com",
                            "Booking Confirmed",
                            "Your booking is confirmed with ID: " + booking.getId()
                    )
            );

            return ResponseEntity.ok(response);
        }
}




