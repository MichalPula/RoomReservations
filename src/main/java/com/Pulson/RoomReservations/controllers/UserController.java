package com.Pulson.RoomReservations.controllers;

import com.Pulson.RoomReservations.entities.User;
import com.Pulson.RoomReservations.models.BasicAccountDataChangeRequest;
import com.Pulson.RoomReservations.models.EmailChangeRequest;
import com.Pulson.RoomReservations.models.PasswordChangeRequest;
import com.Pulson.RoomReservations.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/getBasicAccountData/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BasicAccountDataChangeRequest getBasicAccountData(@PathVariable("id") long userId) throws Exception {
        return userService.getBasicAccountData(userId);
    }

    @PutMapping(value = "/update/basicInfo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean updateBasicInfo(@RequestBody BasicAccountDataChangeRequest basicAccountDataChangeRequest) throws Exception {
        return userService.updateBasicInfo(basicAccountDataChangeRequest);
    }

    @PutMapping(value = "/update/email", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateEmail(@RequestBody EmailChangeRequest emailChangeRequest) throws Exception {
        return userService.updateEmail(emailChangeRequest);
    }

    @PutMapping(value = "/update/password", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePassword(@RequestBody PasswordChangeRequest passwordChangeRequest) throws Exception {
        return userService.updatePassword(passwordChangeRequest);
    }
}
