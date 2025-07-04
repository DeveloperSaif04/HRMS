package com.hrm.service;

import com.hrm.dto.UserDto;
import com.hrm.entity.User;
import com.hrm.exception.UserAlreadyExistsException;
import com.hrm.exception.UserNotFoundException;
import com.hrm.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserDto savedUser(UserDto userDto) {

        if(userRepository.findByEmail(userDto.getEmail()).isPresent()){
            throw new UserAlreadyExistsException("User already exits by the email "+userDto.getEmail());
        }

        String encodedpassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedpassword);

           User user= modelMapper.map(userDto,User.class);

         User savedUser = userRepository.save(user);
        //convert user to dto
         UserDto saveduserDto = modelMapper.map(savedUser, UserDto.class);
        return saveduserDto;
    }


    public boolean deleteUser(Long id) {

        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        else {
            throw new UserNotFoundException("User with id "+id+"not found");
        }
    }


    public UserDto getUser(Long id){
      Optional<User> user  = userRepository.findById(id);
      if(user.isPresent()) {
          UserDto getUserDto = modelMapper.map(user, UserDto.class);
          return getUserDto;
      }else {
          throw new UserNotFoundException("User with id "+id+"not found");
      }
    }

    public  List<UserDto> getAllUser(){
      List<User>  userList = userRepository.findAll();
      return userList.stream().map(user -> modelMapper.map(user, UserDto.class))
              .collect(Collectors.toList());
    }

    public boolean updateUser(Long id,UserDto userDto){
           Optional<User> optionalUser = userRepository.findById(id);
           if(optionalUser.isPresent()){
               User user = optionalUser.get();

               if (userDto.getUsername() != null) {
                   user.setUsername(userDto.getUsername());
               }
               if (userDto.getEmail() != null) {
                   user.setEmail(userDto.getEmail());
               }
               if (userDto.getCity() != null) {
                   user.setCity(userDto.getCity());
               }
               if (userDto.getContact() != null) {
                   user.setContact(userDto.getContact());
               }
                userRepository.save(user);
               return true;
           }else {
               throw  new UserNotFoundException("user not found with id:" +id);
           }
    }
}
