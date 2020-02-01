package com.Pulson.RoomReservations.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(columnDefinition = "text", name = "name")
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(columnDefinition = "integer", name = "user_type_id")
    private UserType userType;

    @NotNull
    @Column(columnDefinition = "boolean default yes", name = "is_available")
    private Boolean isAvailable;

    public Activity() {
    }

    public Activity(String name, UserType userType, Boolean isAvailable){
        this.name = name;
        this.userType = userType;
        this.isAvailable = isAvailable;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
