package com.Pulson.RoomReservations.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(columnDefinition = "text", name = "name")
    private String name;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(targetEntity = Role.class)
    @JoinTable(name = "activities_roles",
            joinColumns = @JoinColumn(name = "activity_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> authorities = new ArrayList<>();

    @Column(columnDefinition = "boolean", name = "is_available")
    private Boolean isAvailable = true;

    public Activity() { }

    public Activity(String name, List<Role> authorities, Boolean isAvailable){
        this.name = name;
        this.authorities = authorities;
        this.isAvailable = isAvailable;
    }

    public Activity(String name, List<Role> authorities){
        this.name = name;
        this.authorities = authorities;
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

    public List<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}
