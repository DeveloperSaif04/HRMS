package com.hrm.controller;

import com.hrm.dto.ResponseDto;
import com.hrm.dto.UserDto;
import com.hrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/delete")
    public ResponseDto deletedUser(@RequestParam Long id){

       boolean deleteUser   = userService.deleteUser(id);
       ResponseDto response= new ResponseDto();
       if (deleteUser){
           response.setStatusCode("200");
           response.setMessage("User deleted");
           response.setResponseData(deleteUser);
       }else {
           response.setStatusCode("404");
           response.setMessage("User not found");
       }
       return response;
    }


    @GetMapping("/get")
    public ResponseDto get(@RequestParam Long id){
      UserDto userDto  =  userService.getUser(id);
      ResponseDto response = new ResponseDto();
      if(userDto != null){
          response.setStatusCode("200");
          response.setMessage("user found...");
          response.setResponseData(userDto);
      }else {
          response.setStatusCode("404");
          response.setMessage("User not found");
          response.setResponseData(null);
      }
      return  response;
    }

    @GetMapping("/getAll")
    public ResponseDto getAll(){
      List<UserDto> userDto = userService.getAllUser();
       ResponseDto response = new ResponseDto();
       if(!userDto.isEmpty()) {
           response.setStatusCode("200");
           response.setMessage("user found...");
           response.setResponseData(userDto);
       }else {
           response.setStatusCode("404");
           response.setMessage("User in database");
           response.setResponseData(null);
       }
       return  response;
    }
}
