package com.Pulson.RoomReservations.user;

import com.Pulson.RoomReservations.authentication.account_data_change.BasicAccountDataChangeRequest;
import com.Pulson.RoomReservations.authentication.account_data_change.EmailChangeRequest;
import com.Pulson.RoomReservations.authentication.account_data_change.PasswordChangeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {

    List<User> getAllStudents();

    List<User> getStudentByName(String firstName, String lastName);

    Boolean create(User user);

    ResponseEntity<?> updateBasicInfo(BasicAccountDataChangeRequest basicAccountInfo);

    ResponseEntity<?> updateEmail(EmailChangeRequest emailChangeRequest);

    ResponseEntity<?> updatePassword(PasswordChangeRequest passwordChangeRequest);

    Boolean existsByUsername(String username);

    UserBasicAccountData getBasicAccountData(long id);
}
