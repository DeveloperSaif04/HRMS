package com.hrm.controller;

import com.hrm.dto.AvailableRoomsDto;
import com.hrm.dto.PropertyDto;
import com.hrm.entity.Property;
import com.hrm.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping("/create")
    public ResponseEntity<Property> create(@RequestBody PropertyDto propertyDto){
      Property savedProperty = propertyService.createProperty(propertyDto);
       return new ResponseEntity<>(savedProperty,HttpStatus.CREATED);
    }


    @GetMapping("/roomAvailable/{hotelId}")
    public ResponseEntity<?> roomAvailable(
            @PathVariable long hotelId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<AvailableRoomsDto> availableRooms = propertyService.roomAvailable(hotelId, startDate, endDate);
        return new ResponseEntity<>(availableRooms, HttpStatus.OK);
    }

}
