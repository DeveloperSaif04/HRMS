package com.hrm.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtService {

    private final static String SECRET_KEY= "test";
    private final static long EXPIRE_TIME=86400000;

    public String generateToken(String username){
      return   JWT.create()
                .withSubject(username)
//                .withClaim("role",role )
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public  String validateToken(String token){
       return JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token)
                .getSubject();
    }
}
