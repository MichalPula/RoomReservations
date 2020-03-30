package com.Pulson.RoomReservations.services;

import com.Pulson.RoomReservations.entities.User;
import com.Pulson.RoomReservations.models.BasicAccountDataChangeRequest;
import com.Pulson.RoomReservations.models.EmailChangeRequest;
import com.Pulson.RoomReservations.models.PasswordChangeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    List<User> getAll();
    User getById(long id) throws Exception;
    Boolean create(User user);
    Boolean deactivate(long id) throws Exception;
    Boolean updateBasicInfo(BasicAccountDataChangeRequest basicAccountInfo) throws Exception;
    Boolean updateEmail(EmailChangeRequest emailChangeRequest) throws Exception;
    ResponseEntity<?> updatePassword(PasswordChangeRequest passwordChangeRequest) throws Exception;
    Boolean existsByUsername(String username);
    BasicAccountDataChangeRequest getBasicAccountData(long id);
}
