package com.Pulson.RoomReservations.services;

import com.Pulson.RoomReservations.models.JwtLoginRequest;
import com.Pulson.RoomReservations.models.JwtRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationServiceImpl implements AuthenticationService{
    @Override
    public ResponseEntity<?> handleLogin(JwtLoginRequest jwtLoginRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> handleRegistration(JwtRegisterRequest jwtRegisterRequest) {
        return null;
    }
}
