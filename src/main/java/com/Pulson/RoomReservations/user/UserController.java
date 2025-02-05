package com.Pulson.RoomReservations.user;

import com.Pulson.RoomReservations.authentication.account_data_change.BasicAccountDataChangeRequest;
import com.Pulson.RoomReservations.authentication.account_data_change.EmailChangeRequest;
import com.Pulson.RoomReservations.authentication.account_data_change.PasswordChangeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("users")
public class UserController {


    private final UserService userService;
    private final ReadUserMapper readUserMapper;

    @Autowired
    public UserController(UserService userService, ReadUserMapper readUserMapper) {
        this.userService = userService;
        this.readUserMapper = readUserMapper;
    }

    @GetMapping(value = "/all/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserReadDTO>> getAllStudents() {
        return ResponseEntity.ok(readUserMapper.mapToUserReadDTOsList(userService.getAllStudents()));
    }

    @GetMapping(value = "/students/name/{firstName}/{lastName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserReadDTO>> getStudentByName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        return ResponseEntity.ok(readUserMapper.mapToUserReadDTOsList(userService.getStudentByName(firstName, lastName)));
    }

    @GetMapping(value = "/getBasicAccountData/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserBasicAccountData> getBasicAccountData(@PathVariable("id") long userId) {
        return ResponseEntity.ok(userService.getBasicAccountData(userId));
    }

    @PutMapping(value = "/update/basicInfo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateBasicInfo(@RequestBody BasicAccountDataChangeRequest basicAccountDataChangeRequest) {
        return userService.updateBasicInfo(basicAccountDataChangeRequest);
    }

    @PutMapping(value = "/update/email", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateEmail(@RequestBody EmailChangeRequest emailChangeRequest) {
        return userService.updateEmail(emailChangeRequest);
    }

    @PutMapping(value = "/update/password", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePassword(@RequestBody PasswordChangeRequest passwordChangeRequest) {
        return userService.updatePassword(passwordChangeRequest);
    }
}
