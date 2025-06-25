package com.hrm.service;

import com.hrm.dto.AvailableRoomsDto;
import com.hrm.dto.PropertyDto;
import com.hrm.dto.RoomsDto;
import com.hrm.entity.*;
import com.hrm.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyService {

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

        return  savedProperty;
    }





    public List<AvailableRoomsDto> roomAvailable(long hotelId, LocalDate startDate, LocalDate endDate) {

        List<AvailableRoomsDto> result = new ArrayList<>();

        List<Rooms> roomsList = roomsRepository.findByPropertyId(hotelId);

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




