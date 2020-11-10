package com.Pulson.RoomReservations.reservation;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final CreateUpdateReservationMapper createUpdateReservationMapper;
    private final ReadReservationMapper readReservationMapper;
    private static final Gson gson = new Gson();

    @Autowired
    public ReservationController(ReservationService reservationService, CreateUpdateReservationMapper createUpdateReservationMapper,
                                 ReadReservationMapper readReservationMapper) {
        this.reservationService = reservationService;
        this.createUpdateReservationMapper = createUpdateReservationMapper;
        this.readReservationMapper = readReservationMapper;
    }

    @GetMapping(value = "/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MarkedReservation>> getAllActive() {
        return ResponseEntity.ok(readReservationMapper.mapToMarkedReservationsList(
                readReservationMapper.mapToReservationReadDTOsList(reservationService.getActive())));
    }

    @GetMapping(value = "/active/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReservationReadDTO>> getUsersActiveReservations(@PathVariable("id") long id) {
        return ResponseEntity.ok(readReservationMapper.mapToReservationReadDTOsList(reservationService.getActiveByUser(id)));
    }

    @GetMapping(value = "/history/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReservationReadDTO>> getUsersReservationsHistory(@PathVariable("id") long id) {
        return ResponseEntity.ok(readReservationMapper.mapToReservationReadDTOsList
                (reservationService.getHistoryByUser(id)));
    }

    @GetMapping(value = "/date/{year}/{month}/{day}/room/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Integer>> getTodayReservationsStartingHoursByRoom(@PathVariable("year") int year, @PathVariable("month") int month,
                                                                 @PathVariable("day") int day, @PathVariable("id") int roomId) {
        return ResponseEntity.ok(reservationService.getStartingHoursListByDateByRoom(year, month, day, roomId));
    }

    @GetMapping(value = "/date/{year}/{month}/{day}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MarkedReservation>> getReservationsByDate(@PathVariable("year") int year, @PathVariable("month") int month,
                                                         @PathVariable("day") int day) {
        return ResponseEntity.ok(readReservationMapper.mapToMarkedReservationsList(readReservationMapper.mapToReservationReadDTOsList(
                reservationService.getByDate(year, month, day))));
    }

    @GetMapping(value = "/date/{year}/{month}/{day}/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> getNumberOfReservationsPerDayPerUser(@PathVariable("year") int year, @PathVariable("month") int month,
                                                        @PathVariable("day") int day, @PathVariable("userId") long userId) {
        return ResponseEntity.ok(reservationService.getAmountByDateByUser(year, month, day, userId));
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody ReservationCreateUpdateDTO reservationCreateUpdateDTO) {
        return ResponseEntity.ok(gson.toJson(reservationService.create(createUpdateReservationMapper.mapToReservation(reservationCreateUpdateDTO))));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        return ResponseEntity.ok(gson.toJson(reservationService.delete(id)));
    }
}
