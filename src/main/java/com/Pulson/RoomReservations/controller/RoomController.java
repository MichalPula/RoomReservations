package com.Pulson.RoomReservations.controller;

import com.Pulson.RoomReservations.model.Room;
import com.Pulson.RoomReservations.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @GetMapping("/{id}")
    public Room getById(@PathVariable("id") long roomId) throws Exception {
        return roomRepository.findById(roomId).orElseThrow(()-> new Exception("Room "+ roomId +" not found"));
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Boolean create(@RequestBody Room room){
        roomRepository.save(room);
        return true;
    }
}
