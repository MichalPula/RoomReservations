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


    public Activity() {
    }

    public Activity(String name, UserType userType){
        this.name = name;
        this.userType = userType;
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


}
