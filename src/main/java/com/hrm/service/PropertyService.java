package com.hrm.service;

import com.hrm.dto.PropertyDto;
import com.hrm.dto.RoomsDto;
import com.hrm.entity.*;
import com.hrm.repository.*;
import com.hrm.specification.PropertySpecification;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.util.List;


@Service
public class PropertyService {

    private static final Logger log = LoggerFactory.getLogger(PropertyService.class);

     @Autowired
     private ModelMapper modelMapper;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private RoomsRepository roomsRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private CityRespository cityRespository;

    @Autowired
    private StateRepository stateRepository;

    public Property createProperty(PropertyDto propertyDto){

        Area area = areaRepository.findByName(propertyDto.getArea());

         City city= cityRespository.findByName( propertyDto.getCity());

        State state = stateRepository.findByName( propertyDto.getState());

        Property property = new Property();
        property.setName(propertyDto.getName());
        property.setNumberOfBathrooms(propertyDto.getNumberOfBathrooms());
        property.setNumberOfGuestAllowed(propertyDto.getNumberOfGuestAllowed());
        property.setNumberOfRooms(propertyDto.getNumberOfRooms());
        property.setNumberOfBeds(propertyDto.getNumberOfBeds());
        property.setArea(area);
        property.setCity(city);
        property.setState(state);
        Property savedProperty =  propertyRepository.save(property);

        //save rooms (one property have multiple rooms)
        for(RoomsDto roomsDto: propertyDto.getRoomsDtoList()){
            Rooms rooms = new Rooms();
            rooms.setProperty(savedProperty);
            rooms.setRoomType(roomsDto.getRoomType());
            rooms.setBasePrice(roomsDto.getBasePrice());
            roomsRepository.save(rooms);
        }
        log.info("property succcessfully created");
        return  savedProperty;
    }


    public List<Property> search(String city, Integer rating, Double minPrice, Double maxPrice) {
        if (rating != null && (rating < 1 || rating > 5)) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }

        Specification<Property> spec = PropertySpecification.filterBy(city, rating, minPrice, maxPrice);
        log.info("Applying search filter: city={}, rating={}, minPrice={}, maxPrice={}", city, rating, minPrice, maxPrice);
        return propertyRepository.findAll(spec);
    }

    public Page<Property> listOfProperty(int pageNo, int pageSize, String sortBy){
        PageRequest pages = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
         Page<Property> pageProperty = propertyRepository.findAll(pages);
         log.info("list of property");
         return  pageProperty;
    }


}


