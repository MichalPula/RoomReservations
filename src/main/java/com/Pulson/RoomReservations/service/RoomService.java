package com.Pulson.RoomReservations.service;

import com.Pulson.RoomReservations.model.Room;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RoomService {
    List<Room> getAll();
    Room getById(long id) throws Exception;
}
