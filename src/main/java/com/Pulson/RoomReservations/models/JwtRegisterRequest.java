package com.Pulson.RoomReservations.models;

import java.util.Set;

public class JwtRegisterRequest {

    private String firstName;
    private String lastName;
    private long phoneNumber;
    private String username;
    private String password;
    private Set<String> roles;

    public JwtRegisterRequest() {}

    public JwtRegisterRequest(String firstName, String lastName, long phoneNumber, String username, String password, Set<String> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
