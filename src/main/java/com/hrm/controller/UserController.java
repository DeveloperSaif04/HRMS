package com.hrm.controller;

import com.hrm.dto.ResponseDto;
import com.hrm.dto.UserDto;
import com.hrm.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseDto createUser(@Valid @RequestBody UserDto userDto){

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

    @PatchMapping("/update")
    public ResponseDto update(@RequestParam Long id,@RequestBody UserDto userDto){
        boolean updateUser = userService.updateUser(id,userDto);
        ResponseDto response = new ResponseDto();
        if(updateUser) {
            response.setStatusCode("200");
            response.setMessage("user update...");
            response.setResponseData(true);
        }
        return  response;

    }
}
