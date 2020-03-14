package com.Pulson.RoomReservations.entities.dtos.reservation;

public class ReservationReadDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String roomName;
    private String startTime;
    private String endTime;
    private String activityName;

    public ReservationReadDTO(long id ,String firstName, String lastName, String roomName, String startTime, String endTime, String activityName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roomName = roomName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityName = activityName;
    }

    public ReservationReadDTO(){ }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}
