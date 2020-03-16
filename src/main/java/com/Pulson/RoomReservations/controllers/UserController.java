package com.Pulson.RoomReservations.controllers;

import com.Pulson.RoomReservations.entities.User;
import com.Pulson.RoomReservations.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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

    @DeleteMapping("/deactivate/{id}")
    public boolean delete(@PathVariable("id") long id) throws Exception {
        return userService.deactivate(id);
    }

    @PutMapping("update/{id}")
    public boolean update(@PathVariable("id") long id, @RequestBody User userDetails) throws Exception {
        return userService.update(id, userDetails);
    }
}
