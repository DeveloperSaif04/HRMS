package com.hrm.controller;

import com.hrm.dto.PropertyDto;
import com.hrm.entity.Property;
import com.hrm.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
}

