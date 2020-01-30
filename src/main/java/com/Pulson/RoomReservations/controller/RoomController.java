package com.Pulson.RoomReservations.controller;

import com.Pulson.RoomReservations.model.Room;
import com.Pulson.RoomReservations.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/all")
    public List<Room> getAll(){
        return roomRepository.findAll();
    }
}
