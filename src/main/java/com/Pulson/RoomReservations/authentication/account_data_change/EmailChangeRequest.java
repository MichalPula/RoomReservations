package com.Pulson.RoomReservations.authentication.account_data_change;

import java.io.Serializable;

public class EmailChangeRequest implements Serializable {

    private long userId;
    private String email;

    public EmailChangeRequest(long userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public EmailChangeRequest() {
    }

    public String getEmail() {
        return email;
    }

    public long getUserId() {
        return userId;
    }
}
