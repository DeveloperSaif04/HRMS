package com.hrm.service;

import com.hrm.dto.AuthDto;
import com.hrm.exception.InvaildCreadential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthService {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    public String login(AuthDto authDto) {

         Authentication authentication = authManager.authenticate(
                 new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword())
         );

         if (authentication.isAuthenticated()) {
             String token = jwtService.generateToken(authDto.getUsername());
             return token;
         }
     return null;
    }
    }
