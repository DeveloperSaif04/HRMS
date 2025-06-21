package com.hrm.service;

import com.hrm.dto.UserDto;
import com.hrm.entity.User;
import com.hrm.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserDto savedUser(UserDto userDto) {
        //convert dto to user
        User user =  modelMapper.map(userDto, User.class);
         User savedUser = userRepository.save(user);
        //convert user to dto
         UserDto saveduserDto = modelMapper.map(savedUser, UserDto.class);
        return saveduserDto;
    }
}
