package com.hrm.service;

import com.hrm.dto.AvailableRoomsDto;
import com.hrm.dto.PropertyDto;
import com.hrm.dto.RoomsDto;
import com.hrm.entity.*;
import com.hrm.repository.*;
import com.hrm.specification.PropertySpecification;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.ArrayList;
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
    @Autowired
    private AvailableRoomsRepository availableRoomsRepository;

    public Property createProperty(PropertyDto propertyDto){
        log.info("admin entered createPropery method");

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
            log.info("property successfully created by admin");
            roomsRepository.save(rooms);
        }
        log.info("property succcessfully created");
        return  savedProperty;
    }


    public Page<Property> propertySearch(String city, Integer rating, Double minPrice, Double maxPrice, Pageable pageable) {
        if (rating != null && (rating < 1 || rating > 5)) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }

        Specification<Property> spec = PropertySpecification.filterBy(city, rating, minPrice, maxPrice);
        log.info("Applying search filter: city={}, rating={}, minPrice={}, maxPrice={}, pageable={}", city, rating, minPrice, maxPrice, pageable);

        return propertyRepository.findAll(spec, pageable);
    }

    public Page<Property> listOfProperty(int pageNo, int pageSize, String sortBy){
        PageRequest pages = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
         Page<Property> pageProperty = propertyRepository.findAll(pages);
         log.info("list of property");
         return  pageProperty;
    }

    public List<AvailableRoomsDto> roomAvailable(long propertyId, LocalDate startDate, LocalDate endDate) {

        List<AvailableRoomsDto> result = new ArrayList<>();

        List<Rooms> roomsList = roomsRepository.findByPropertyId(propertyId);

        for (Rooms room : roomsList) {
            List<AvailableRooms> availableRooms = availableRoomsRepository
                    .findAvailableRooms(room, startDate, endDate);

            for (AvailableRooms available : availableRooms) {
                AvailableRoomsDto dto = new AvailableRoomsDto();
                dto.setRoomType(room.getRoomType());
                dto.setPrice(available.getPrice());
                dto.setAvailableDate(available.getAvailableDate());
                result.add(dto);
            }
        }

        return result;
    }

}





