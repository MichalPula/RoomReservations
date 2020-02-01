package com.Pulson.RoomReservations.service;

import com.Pulson.RoomReservations.model.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {
    List<Room> getAll();
    Room getById(long id) throws Exception;
    Boolean create(Room room);
    Boolean delete(long id) throws Exception;
}
