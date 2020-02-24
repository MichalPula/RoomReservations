package com.Pulson.RoomReservations.models;

import java.io.Serializable;
import java.util.List;

public class JwtResponse implements Serializable {

    private final String jwttoken;
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String jwttoken, Long id, String username, String email, List<String> roles) {
        this.jwttoken = jwttoken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public String getJwttoken() {
        return jwttoken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }
}
