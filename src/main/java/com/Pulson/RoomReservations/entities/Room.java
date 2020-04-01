package com.Pulson.RoomReservations.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(columnDefinition = "text", name = "name", unique = true)
    private String name;

    @Column(columnDefinition = "boolean", name = "is_available")
    private Boolean isAvailable;

    public Room() {
    }

    public Room(String name, Boolean isAvailable){
        this.name = name;
        this.isAvailable = isAvailable;
    }

    public Room(String name){
        this.name = name;
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

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}
