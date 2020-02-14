package com.Pulson.RoomReservations.services;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public class JwtTokenServiceImpl implements JwtTokenService {
    @Override
    public Date getExpirationDateFromToken(String token) {
        return null;
    }

    @Override
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        return null;
    }

    @Override
    public Claims getAllClaimsFromToken(String token) {
        return null;
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return null;
    }

    @Override
    public String doGenerateToken(Map<String, Object> claims, String subject) {
        return null;
    }

    @Override
    public Boolean validateToken(String token, UserDetails userDetails) {
        return null;
    }

    @Override
    public Boolean isTokenExpired(String token) {
        return null;
    }

    @Override
    public String getUsernameFromToken(String token) {
        return null;
    }
}
