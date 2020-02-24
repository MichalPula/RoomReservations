package com.Pulson.RoomReservations.services;

import com.Pulson.RoomReservations.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    List<User> getAll();
    User getById(long id) throws Exception;
    Boolean create(User user);
    Boolean deactivate(long id) throws Exception;
    Boolean update(long id, User userDetails) throws Exception;
    User findUserByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
