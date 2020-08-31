package com.Pulson.RoomReservations.activity;

public class ActivityNotFoundException extends RuntimeException{
    public ActivityNotFoundException(Long id) {
            super("Activity with id: " + id + " not found");
    }
}
