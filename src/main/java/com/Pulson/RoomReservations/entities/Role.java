package com.Pulson.RoomReservations.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(columnDefinition = "text", name = "role", unique = true)
    private String role;

    public Role() {
    }

    public Role(String role){
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return role;
    }

    public void setName(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return this.role;
    }
}
