package com.hrm.controller;

import com.hrm.dto.PropertyDto;
import com.hrm.entity.Property;
import com.hrm.service.PropertyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
@Validated
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
        List<Property> result = propertyService.search(city ,rating,minPrice, maxPrice);
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
}

