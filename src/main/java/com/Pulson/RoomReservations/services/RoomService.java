package com.Pulson.RoomReservations.services;

import com.Pulson.RoomReservations.entities.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {
    List<Room> getAll();
    Room getById(long id) throws Exception;
    Boolean create(Room room);
    Boolean deactivate(long id) throws Exception;
    Boolean update(long id, Room roomDetails) throws Exception;
}
