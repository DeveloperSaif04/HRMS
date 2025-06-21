package com.hrm.controller;

import com.hrm.dto.ResponseDto;
import com.hrm.dto.UserDto;
import com.hrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseDto createUser(@RequestBody UserDto userDto){

            UserDto createdUser   = userService.savedUser(userDto);

            ResponseDto response = new ResponseDto();
            response.setStatusCode("201");
            response.setMessage("user created successfully");
            response.setResponseData(createdUser);
            return response;
    }
}
