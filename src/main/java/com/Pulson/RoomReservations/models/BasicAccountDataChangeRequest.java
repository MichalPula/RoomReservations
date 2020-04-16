package com.Pulson.RoomReservations.models;

import java.io.Serializable;

public class BasicAccountDataChangeRequest implements Serializable {

    private long userId;
    private String firstName;
    private String lastName;
    private long phoneNumber;

    public BasicAccountDataChangeRequest(long userId, String firstName, String lastName, long phoneNumber) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public BasicAccountDataChangeRequest(String firstName, String lastName, long phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public BasicAccountDataChangeRequest() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public long getUserId() {
        return userId;
    }
}
