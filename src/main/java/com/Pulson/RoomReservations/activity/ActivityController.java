package com.Pulson.RoomReservations.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public List<ActivityCreateReadUpdateDTO> getAll() {
        return readActivityMapper.mapToActivityCreateReadUpdateDTOsList(activityService.getAll());
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean create(@RequestBody ActivityCreateReadUpdateDTO activityCreateReadUpdateDTO) throws Exception {
        return activityService.create(createUpdateActivityMapper.mapToActivity(activityCreateReadUpdateDTO));
    }

    @DeleteMapping("/deactivate/{id}")
    public boolean deactivate(@PathVariable("id") long id) throws Exception {
        return activityService.deactivate(id);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean update(@PathVariable("id") long id, @RequestBody ActivityCreateReadUpdateDTO activityCreateReadUpdateDTO) throws Exception {
        return activityService.update(id, createUpdateActivityMapper.mapToActivity(activityCreateReadUpdateDTO));
    }
}
