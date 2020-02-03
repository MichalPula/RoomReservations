package com.Pulson.RoomReservations.service;

import com.Pulson.RoomReservations.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAll();
}
