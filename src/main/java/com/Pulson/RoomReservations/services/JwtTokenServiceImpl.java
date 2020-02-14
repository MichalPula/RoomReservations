package com.Pulson.RoomReservations.services;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenServiceImpl implements JwtTokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    @Override
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
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
