package com.Pulson.RoomReservations.Reservation;

public class ReservationCreateUpdateDTO {

    private Long userId;
    private String roomName;
    private String startTime;
    private String endTime;
    private String activityName;

    public ReservationCreateUpdateDTO(Long userId, String roomName, String startTime, String endTime, String activityName) {
        this.userId = userId;
        this.roomName = roomName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityName = activityName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}
