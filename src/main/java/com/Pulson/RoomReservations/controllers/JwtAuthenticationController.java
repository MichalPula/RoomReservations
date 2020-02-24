package com.Pulson.RoomReservations.controllers;

import com.Pulson.RoomReservations.models.JwtLoginRequest;
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

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtLoginRequest authenticationRequest) throws Exception {
        return authenticationService.handleLogin(authenticationRequest);
    }

}
