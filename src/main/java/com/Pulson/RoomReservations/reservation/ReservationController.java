package com.Pulson.RoomReservations.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("reservations")
public class ReservationController {

    private ReservationService reservationService;
    private CreateUpdateReservationMapper createUpdateReservationMapper;
    private ReadReservationMapper readReservationMapper;

    @Autowired
    public ReservationController(ReservationService reservationService, CreateUpdateReservationMapper createUpdateReservationMapper,
                                 ReadReservationMapper readReservationMapper) {
        this.reservationService = reservationService;
        this.createUpdateReservationMapper = createUpdateReservationMapper;
        this.readReservationMapper = readReservationMapper;
    }


    @GetMapping(value = "/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MarkedReservation> getAllActive() {
        return readReservationMapper.mapToMarkedReservationsList(readReservationMapper.mapToReservationReadDTOsList(
                reservationService.getActive()));
    }

    @GetMapping(value = "/active/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReservationReadDTO> getUsersActiveReservations(@PathVariable("id") long id) throws Exception {
        return readReservationMapper.mapToReservationReadDTOsList(reservationService.getActiveByUser(id));
    }

    @GetMapping(value = "/history/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReservationReadDTO> getUsersReservationsHistory(@PathVariable("id") long id) throws Exception {
        return readReservationMapper.mapToReservationReadDTOsList(reservationService.getHistoryByUser(id));
    }

    @GetMapping(value = "/date/{year}/{month}/{day}/room/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Integer> getTodayReservationsStartingHoursByRoom(@PathVariable("year") int year, @PathVariable("month") int month,
                                                                 @PathVariable("day") int day, @PathVariable("id") int roomId) throws Exception {
        return reservationService.getStartingHoursListByDateByRoom(year, month, day, roomId);
    }

    @GetMapping(value = "/date/{year}/{month}/{day}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MarkedReservation> getReservationsByDate(@PathVariable("year") int year, @PathVariable("month") int month,
                                                         @PathVariable("day") int day) throws Exception {
        return readReservationMapper.mapToMarkedReservationsList(readReservationMapper.mapToReservationReadDTOsList(
                reservationService.getByDate(year, month, day)));
    }

    @GetMapping(value = "/date/{year}/{month}/{day}/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer getNumberOfReservationsPerDayPerUser(@PathVariable("year") int year, @PathVariable("month") int month,
                                                        @PathVariable("day") int day, @PathVariable("userId") long userId) throws Exception {
        return reservationService.getAmountByDateByUser(year, month, day, userId);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean create(@RequestBody ReservationCreateUpdateDTO reservationCreateUpdateDTO) throws Exception {
        return reservationService.create(createUpdateReservationMapper.mapToReservation(reservationCreateUpdateDTO));
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") long id) throws Exception {
        return reservationService.delete(id);
    }
}
