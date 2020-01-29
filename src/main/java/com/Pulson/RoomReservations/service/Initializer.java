package com.Pulson.RoomReservations.service;

import com.Pulson.RoomReservations.model.User;
import com.Pulson.RoomReservations.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class Initializer {
    public Initializer(UserRepository userRepository){
        userRepository.save(new User("LeBron", "James", "lebron@gmail.com", 2));
        userRepository.save(new User("Kevin", "Durant", "kd@gmail.com", 1));
        userRepository.save(new User("Michael", "Duke", "steph_curry@gmail.com", 2));
        userRepository.save(new User("Kyrie", "Irving", "kyrie_irvingh@gmail.com", 1));
        userRepository.save(new User("Derrick", "Rose", "drose@gmail.com", 2));
    }
}
