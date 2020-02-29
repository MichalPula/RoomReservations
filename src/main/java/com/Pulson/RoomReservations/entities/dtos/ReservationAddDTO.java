package com.Pulson.RoomReservations.entities.dtos;

import java.time.ZonedDateTime;

public class ReservationAddDTO {

    private Long userId;
    private Long roomId;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    private Long activityId;

    public ReservationAddDTO(Long userId, Long roomId, ZonedDateTime startTime, ZonedDateTime endTime, Long activityId) {
        this.userId = userId;
        this.roomId = roomId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityId = activityId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
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

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
