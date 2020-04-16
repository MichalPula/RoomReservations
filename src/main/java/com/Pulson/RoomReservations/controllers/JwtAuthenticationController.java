package com.Pulson.RoomReservations.controllers;

import com.Pulson.RoomReservations.models.JwtLoginRequest;
import com.Pulson.RoomReservations.models.JwtRegisterRequest;
import com.Pulson.RoomReservations.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class JwtAuthenticationController {


    private AuthenticationService authenticationService;

    @Autowired
    public JwtAuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtLoginRequest loginRequest) throws Exception {
        return authenticationService.handleLogin(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody JwtRegisterRequest registerRequest) throws Exception {
        return authenticationService.handleRegistration(registerRequest);
    }
}
