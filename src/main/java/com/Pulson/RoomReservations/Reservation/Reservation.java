package com.Pulson.RoomReservations.Reservation;

import com.Pulson.RoomReservations.Activity.Activity;
import com.Pulson.RoomReservations.Room.Room;
import com.Pulson.RoomReservations.User.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(columnDefinition = "integer", name = "user_id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(columnDefinition = "integer", name = "room_id")
    private Room room;

    @NotNull
    @Column(columnDefinition = "timestamp with time zone", name = "start_time")
    private LocalDateTime startTime;

    @NotNull
    @Column(columnDefinition = "timestamp with time zone", name = "end_time")
    private LocalDateTime endTime;

    @NotNull
    @ManyToOne
    @JoinColumn(columnDefinition = "integer", name = "activity_id")
    private Activity activity;

    public Reservation() {
    }

    public Reservation(User user, Room room, LocalDateTime startTime, LocalDateTime endTime, Activity activity) {
        this.user = user;
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
