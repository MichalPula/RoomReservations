package com.Pulson.RoomReservations.room;

import com.google.gson.Gson;
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
    private static final Gson gson = new Gson();

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Room>> getAll() {
        return ResponseEntity.ok().body(roomService.getAll());
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody Room room) {
        return ResponseEntity.ok(gson.toJson(roomService.create(room)));
    }

    @DeleteMapping(value = "/deactivate/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deactivate(@PathVariable("id") long id) {
        return ResponseEntity.ok(gson.toJson(roomService.deactivate(id)));
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@PathVariable("id") long id, @RequestBody Room roomDetails) {
        return ResponseEntity.ok(gson.toJson(roomService.update(id, roomDetails)));
    }
}
