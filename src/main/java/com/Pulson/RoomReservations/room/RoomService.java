package com.Pulson.RoomReservations.room;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {
    List<Room> getAll();

    String create(Room room);

    String deactivate(long id);

    String update(long id, Room roomDetails);
}
