package com.Pulson.RoomReservations.controller;

import com.Pulson.RoomReservations.model.User;
import com.Pulson.RoomReservations.repository.UserRepository;
import com.Pulson.RoomReservations.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") long id) throws Exception {
        return userService.getById(id);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean create(@RequestBody User user) {
        return userService.create(user);

    }

    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable("id") long userId) throws Exception {
        userRepository.delete(userRepository.findById(userId).orElseThrow(() -> new Exception("User has NOT been removed")));
        return true;
    }

    @PutMapping("update/{id}")
    public Boolean update(@PathVariable("id") long userId, @RequestBody User userDetails) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User NOT found"));
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEMail(userDetails.getEMail());
        userRepository.save(user);
        return true;
    }
}
