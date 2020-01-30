package com.Pulson.RoomReservations.service;

import com.Pulson.RoomReservations.model.Activity;
import com.Pulson.RoomReservations.model.Room;
import com.Pulson.RoomReservations.model.User;
import com.Pulson.RoomReservations.repository.ActivityRepository;
import com.Pulson.RoomReservations.repository.RoomRepository;
import com.Pulson.RoomReservations.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class Initializer {
    public Initializer(UserRepository userRepository, RoomRepository roomRepository, ActivityRepository activityRepository){
        userRepository.save(new User("LeBron", "James", "lebron@gmail.com", 2));
        userRepository.save(new User("Kevin", "Durant", "kd@gmail.com", 1));
        userRepository.save(new User("Stephen", "Curry", "steph_curry@gmail.com", 2));
        userRepository.save(new User("Kyrie", "Irving", "kyrie_irvingh@gmail.com", 1));
        userRepository.save(new User("Derrick", "Rose", "drose@gmail.com", 2));

        roomRepository.save(new Room("Small wood room 1"));
        roomRepository.save(new Room("Small wood room 2"));
        roomRepository.save(new Room("Jerzy's office"));

        activityRepository.save(new Activity("learning",2));
        activityRepository.save(new Activity("playing PS4",2));
        activityRepository.save(new Activity("helping other student",2));
        activityRepository.save(new Activity("reading a book",2));
        activityRepository.save(new Activity("reading a book",2));
        activityRepository.save(new Activity("playing board games",2));
        activityRepository.save(new Activity("making my SI assignment",2));
        activityRepository.save(new Activity("making TW assingment with my team",2));
        activityRepository.save(new Activity("making a code review",1));
        activityRepository.save(new Activity("having 1 on 1 talk",1));
        activityRepository.save(new Activity("having QualityGate",1));
    }
}
