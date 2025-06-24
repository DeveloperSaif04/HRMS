package com.hrm.controller;

import com.hrm.dto.PropertyDto;
import com.hrm.entity.Property;
import com.hrm.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/search")
    public ResponseEntity<List<Property>> searchHotels(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice
    ) {
        List<Property> result = propertyService.search(city, rating, minPrice, maxPrice);
        return ResponseEntity.ok(result);
    }
}

