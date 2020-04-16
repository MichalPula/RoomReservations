package com.Pulson.RoomReservations.services;

import com.Pulson.RoomReservations.models.JwtLoginRequest;
import com.Pulson.RoomReservations.models.JwtRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface AuthenticationService {
    ResponseEntity<?> handleLogin(JwtLoginRequest jwtLoginRequest) throws Exception;

    ResponseEntity<?> handleRegistration(JwtRegisterRequest jwtRegisterRequest);
}
