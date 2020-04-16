package com.Pulson.RoomReservations.services;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
public interface JwtTokenService {
    Date getExpirationDateFromToken(String token);

    <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver);

    Claims getAllClaimsFromToken(String token);

    String generateToken(UserDetails userDetails);

    String doGenerateToken(Map<String, Object> claims, String subject);

    Boolean validateToken(String token, UserDetails userDetails);

    Boolean isTokenExpired(String token);

    String getUsernameFromToken(String token);
}
