package com.Pulson.RoomReservations.room;

public class RoomNotFoundException extends RuntimeException{
    public RoomNotFoundException(Long id) {
        super("Room with id: " + id + " not found");
    }
}
