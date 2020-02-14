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
    @Column(columnDefinition = "text", name = "nick_name")
    private String nickName;

    @NotNull
    @Column(columnDefinition = "text", name = "e_mail")
    private String eMail;

    @NotNull
    @ManyToOne
    @JoinColumn(columnDefinition = "integer", name = "user_type_id")
    private Role role;

    @Column(columnDefinition = "boolean", name = "is_active")
    private Boolean isActive = true;

    public User() {
    }

    public User(String firstName, String lastName, String nickName, String eMail, Role role, Boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.eMail = eMail;
        this.role = role;
        this.isActive = isActive;
    }

    public User(String firstName, String lastName, String nickName, String eMail, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.eMail = eMail;
        this.role = role;
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


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
