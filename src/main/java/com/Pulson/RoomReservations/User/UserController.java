package com.Pulson.RoomReservations.User;

import com.Pulson.RoomReservations.models.BasicAccountDataChangeRequest;
import com.Pulson.RoomReservations.models.EmailChangeRequest;
import com.Pulson.RoomReservations.models.PasswordChangeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("users")
public class UserController {


    private UserService userService;
    private ReadUserMapper readUserMapper;

    @Autowired
    public UserController(UserService userService, ReadUserMapper readUserMapper) {
        this.userService = userService;
        this.readUserMapper = readUserMapper;
    }

    @GetMapping("/all/students")
    public List<UserReadDTO> getAllStudents() {
        return readUserMapper.mapToUserReadDTOsList(userService.getAllStudents());
    }

    @GetMapping("/students/name/{firstName}/{lastName}")
    public List<UserReadDTO> getStudentByName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) throws Exception {
        return readUserMapper.mapToUserReadDTOsList(userService.getStudentByName(firstName, lastName));
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
