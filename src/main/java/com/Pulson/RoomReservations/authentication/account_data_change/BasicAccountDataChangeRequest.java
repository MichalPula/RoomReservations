package com.Pulson.RoomReservations.authentication.account_data_change;

import java.io.Serializable;

public class BasicAccountDataChangeRequest implements Serializable {

    private long userId;
    private String firstName;
    private String lastName;
    private long phoneNumber;


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
