package com.Pulson.RoomReservations.user;

public class UserBasicAccountData {

    private String firstName;
    private String lastName;
    private long phoneNumber;

    public UserBasicAccountData(String firstName, String lastName, long phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
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
}
