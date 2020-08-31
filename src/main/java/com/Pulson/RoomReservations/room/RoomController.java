package com.Pulson.RoomReservations.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Room>> getAll() {
        return ResponseEntity.ok().body(roomService.getAll());
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody Room room) {
        return ResponseEntity.ok().body(roomService.create(room));
    }

    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<?> deactivate(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(roomService.deactivate(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Room roomDetails) {
        return ResponseEntity.ok().body(roomService.update(id, roomDetails));
    }
}
