package com.Pulson.RoomReservations.entities.dtos;

import java.time.ZonedDateTime;

public class ReservationReadDTO {
    private String firstName;
    private String lastName;
    private String roomName;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    private String activityName;

    public ReservationReadDTO(String firstName, String lastName, String roomName, ZonedDateTime startTime, ZonedDateTime endTime, String activityName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roomName = roomName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityName = activityName;
    }

    public ReservationReadDTO(){ }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}
