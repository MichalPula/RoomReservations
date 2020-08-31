package com.Pulson.RoomReservations.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("activities")
public class ActivityController {


    private final ActivityService activityService;
    private final CreateUpdateActivityMapper createUpdateActivityMapper;
    private final ReadActivityMapper readActivityMapper;

    @Autowired
    public ActivityController(ActivityService activityService, CreateUpdateActivityMapper createUpdateActivityMapper,
                              ReadActivityMapper readActivityMapper) {
        this.activityService = activityService;
        this.createUpdateActivityMapper = createUpdateActivityMapper;
        this.readActivityMapper = readActivityMapper;
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ActivityCreateReadUpdateDTO>> getAll() {
        return ResponseEntity.ok(readActivityMapper.mapToActivityCreateReadUpdateDTOsList(activityService.getAll()));
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody ActivityCreateReadUpdateDTO activityCreateReadUpdateDTO) {
        return ResponseEntity.ok(activityService.create(createUpdateActivityMapper.mapToActivity(activityCreateReadUpdateDTO)));
    }

    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<String> deactivate(@PathVariable("id") long id) throws Exception {
        return ResponseEntity.ok(activityService.deactivate(id));
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@PathVariable("id") long id, @RequestBody ActivityCreateReadUpdateDTO activityCreateReadUpdateDTO) {
        return ResponseEntity.ok(activityService.update(id, createUpdateActivityMapper.mapToActivity(activityCreateReadUpdateDTO)));
    }
}
