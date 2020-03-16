package com.Pulson.RoomReservations.entities.dtos.activity;

import java.util.List;

public class ActivityCreateReadUpdateDTO {

    private String name;
    private List<String> authorities;
    private Boolean available;

    public ActivityCreateReadUpdateDTO(String name, List<String> authorities, Boolean available) {
        this.name = name;
        this.authorities = authorities;
        this.available = available;
    }

    public ActivityCreateReadUpdateDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
