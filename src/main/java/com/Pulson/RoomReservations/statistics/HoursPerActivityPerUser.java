package com.Pulson.RoomReservations.statistics;

public class HoursPerActivityPerUser {

    private Integer y;
    private String activity;

    public HoursPerActivityPerUser(Integer y, String activity) {
        this.y = y;
        this.activity = activity;
    }

    public Integer getY() {
        return y;
    }

    public String getActivity() {
        return activity;
    }
}
