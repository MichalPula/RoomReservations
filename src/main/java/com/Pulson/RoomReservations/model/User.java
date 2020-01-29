package com.Pulson.RoomReservations.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(columnDefinition = "text", name = "first_name")
    private String firstName;

    @Column(columnDefinition = "text", name = "last_name")
    private String lastName;

    @Column(columnDefinition = "text", name = "e-mail")
    private String eMail;

    @Column(columnDefinition = "integer", name = "user_type_id")
    private int userTypeId;

    public User() {
    }

    public User(String firstName, String lastName, String eMail, int userTypeId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.userTypeId = userTypeId;
    }

    public User(long id, String firstName, String lastName, String eMail, int userTypeId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.userTypeId = userTypeId;
    }

    public long getId() {
        return id;
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

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }
}
