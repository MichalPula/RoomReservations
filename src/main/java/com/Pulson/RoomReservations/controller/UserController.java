package com.Pulson.RoomReservations.controller;

import com.Pulson.RoomReservations.model.User;
import com.Pulson.RoomReservations.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User " + userId + " not found"));
        return ResponseEntity.ok().body(user);
    }

}
