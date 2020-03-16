package com.Pulson.RoomReservations.services;

import com.Pulson.RoomReservations.entities.*;
import com.Pulson.RoomReservations.repositories.ActivityRepository;
import com.Pulson.RoomReservations.repositories.ReservationRepository;
import com.Pulson.RoomReservations.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getActive() {
        List<Reservation> activeReservations = new ArrayList<>();
        reservationRepository.findAll().forEach(reservation -> {
            if(reservation.getStartTime().isAfter(LocalDateTime.now())){
                activeReservations.add(reservation);
            }
        });
        return activeReservations;
    }

    @Override
    public List<Reservation> getActiveByUser(long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User " + userId + " NOT found"));
        List<Reservation> activeReservations = new ArrayList<>();
        reservationRepository.findAllByUser(user).forEach(reservation -> {
            if(reservation.getStartTime().isAfter(LocalDateTime.now())){
                activeReservations.add(reservation);
            }
        });
        return activeReservations;
    }

    @Override
    public List<Reservation> getHistoryByUser(long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User " + userId + " NOT found"));
        List<Reservation> reservationsHistory = new ArrayList<>();
        reservationRepository.findAllByUser(user).forEach(reservation -> {
            if(reservation.getEndTime().isBefore(LocalDateTime.now())){
                reservationsHistory.add(reservation);
            }
        });
        return reservationsHistory;
    }

    @Override
    public List<Integer> getStartingHoursListByDate(int year, int month, int day) throws Exception {
        List<Reservation> todayReservations = reservationRepository.findAllByStartTimeBetween(LocalDateTime.of(year,month,day,0,0), LocalDateTime.of(year,month,day,23,59));
        List<Integer> todayReservationsStartingHours = new ArrayList<>();
        todayReservations.forEach(reservation -> {
            todayReservationsStartingHours.add(reservation.getStartTime().getHour());
        });
        return todayReservationsStartingHours;
    }

    @Override
    public Integer getAmountByDateByUser(int year, int month, int day, long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User " + userId + " NOT found"));
        return reservationRepository.countAllByStartTimeBetweenAndUser(LocalDateTime.of(year,month,day,0,0), LocalDateTime.of(year,month,day,23,59), user);
    }

    @Override
    public Reservation getById(long id) throws Exception {
        return reservationRepository.findById(id).orElseThrow(() -> new Exception("Reservation " + id + " NOT found"));
    }

    @Override
    @Transactional
    public Boolean create(Reservation reservation) throws Exception {
        reservationRepository.save(reservation);
        return true;
    }

    @Override
    @Transactional
    public Boolean update(long id, Reservation reservationDetails) throws Exception {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new Exception("Reservation NOT found"));
        reservation.setUser(reservationDetails.getUser());
        reservation.setRoom(reservationDetails.getRoom());
        reservation.setStartTime(reservationDetails.getStartTime());
        reservation.setEndTime(reservationDetails.getEndTime());
        reservation.setActivity(reservationDetails.getActivity());
        reservationRepository.save(reservation);
        return true;
    }

    @Override
    @Transactional
    public Boolean delete(long id) {
        reservationRepository.deleteById(id);
        return true;
    }
}
