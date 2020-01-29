package com.Pulson.RoomReservations.controller;

import com.Pulson.RoomReservations.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(name = "users")
public class UserController {

    @Autowired
    private UserRepository userRepository;



}
