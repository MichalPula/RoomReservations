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

    @NotNull
    @Column(columnDefinition = "text", name = "last_name")
    private String lastName;

    @NotNull
    @Column(columnDefinition = "text", name = "e_mail")
    private String eMail;

    @NotNull
    @ManyToOne
    @JoinColumn(columnDefinition = "integer", name = "user_type_id")
    private UserType userType;

    @Column(columnDefinition = "boolean", name = "is_active")
    private Boolean isActive = true;

    public User() {
    }

    public User(String firstName, String lastName, String eMail, UserType userType, Boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.userType = userType;
        this.isActive = isActive;
    }

    public User(long id, String firstName, String lastName, String eMail, UserType userType,  Boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.userType = userType;
        this.isActive = isActive;
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


    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
