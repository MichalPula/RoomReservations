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
    @Column(columnDefinition = "integer", name = "user_type_id")
    private int userTypeId;


    public Activity() {
    }

    public Activity(String name, int userTypeId){
        this.name = name;
        this.userTypeId = userTypeId;
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

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }
}
