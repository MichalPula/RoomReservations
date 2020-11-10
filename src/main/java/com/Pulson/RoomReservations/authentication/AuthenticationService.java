package com.Pulson.RoomReservations.authentication;

import com.Pulson.RoomReservations.authentication.jwt_models.JwtLoginRequest;
import com.Pulson.RoomReservations.authentication.jwt_models.JwtRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface AuthenticationService {
    ResponseEntity<?> handleLogin(JwtLoginRequest jwtLoginRequest);

    ResponseEntity<?> handleRegistration(JwtRegisterRequest jwtRegisterRequest);
}
