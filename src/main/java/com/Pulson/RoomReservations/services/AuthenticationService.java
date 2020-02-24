package com.Pulson.RoomReservations.services;

import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<?> handleLogin();
    ResponseEntity<?> handleRegistration();
}
