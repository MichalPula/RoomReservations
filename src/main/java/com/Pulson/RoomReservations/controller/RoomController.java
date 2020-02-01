package com.Pulson.RoomReservations.controller;

import com.Pulson.RoomReservations.model.Room;
import com.Pulson.RoomReservations.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/all")
    public List<Room> getAll(){
        return roomService.getAll();
    }

    @GetMapping("/{id}")
    public Room getById(@PathVariable("id") long id) throws Exception {
        return roomService.getById(id);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean create(@RequestBody Room room){
        roomRepository.save(room);
        return true;
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") long roomId) throws Exception {
        roomRepository.delete(roomRepository.findById(roomId).orElseThrow(() -> new Exception("Room has NOT been removed")));
        return true;
    }

    @PutMapping("/update/{id}")
    public boolean update(@PathVariable("id") long roomId, @RequestBody Room roomDetails) throws Exception {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new Exception("Room NOT found"));
        room.setName(roomDetails.getName());
        roomRepository.save(room);
        return true;
    }
}
