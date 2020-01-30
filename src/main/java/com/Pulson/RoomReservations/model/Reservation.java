package com.Pulson.RoomReservations.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(columnDefinition = "integer", name = "user_id")
    private int userId;

    @NotNull
    @Column(columnDefinition = "integer", name = "room_id")
    private int roomId;

    @NotNull
    @Column(columnDefinition = "timestamp with time zone", name = "start_time")
    private ZonedDateTime startTime;

    @NotNull
    @Column(columnDefinition = "timestamp with time zone", name = "end_time")
    private ZonedDateTime endTime;

    @NotNull
    @Column(columnDefinition = "integer", name = "activity_id")
    private int activityId;

    public Reservation() {
    }

    public Reservation(int userId, int roomId, ZonedDateTime startTime, ZonedDateTime endTime, int activityId) {
        this.userId = userId;
        this.roomId = roomId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityId = activityId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
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

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }
}
