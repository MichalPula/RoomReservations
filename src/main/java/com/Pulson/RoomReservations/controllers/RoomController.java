package com.Pulson.RoomReservations.controllers;

import com.Pulson.RoomReservations.entities.Room;
import com.Pulson.RoomReservations.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/all")
    public List<Room> getAll(){
        return roomService.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Room getById(@PathVariable("id") long id) throws Exception {
        return roomService.getById(id);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean create(@RequestBody Room room){
        return roomService.create(room);
    }

    @DeleteMapping("/deactivate/{id}")
    public boolean delete(@PathVariable("id") long id) throws Exception {
        return roomService.deactivate(id);
    }

    @PutMapping("/update/{id}")
    public boolean update(@PathVariable("id") long id, @RequestBody Room roomDetails) throws Exception {
        return roomService.update(id, roomDetails);
    }
}
