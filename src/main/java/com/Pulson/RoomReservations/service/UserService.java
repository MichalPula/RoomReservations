package com.Pulson.RoomReservations.service;

import com.Pulson.RoomReservations.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAll();
    User getById(long id) throws Exception;
    Boolean create(User user);
    Boolean deactivate(long id) throws Exception;
    Boolean update(long id, User userDetails) throws Exception;
}
