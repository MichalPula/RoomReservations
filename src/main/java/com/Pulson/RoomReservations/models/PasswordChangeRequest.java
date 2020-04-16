package com.Pulson.RoomReservations.models;

import java.io.Serializable;

public class PasswordChangeRequest implements Serializable {

    private long userId;
    private String oldPassword;
    private String newPassword;

    public PasswordChangeRequest(long userId, String oldPassword, String newPassword) {
        this.userId = userId;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public PasswordChangeRequest() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public long getUserId() {
        return userId;
    }
}
