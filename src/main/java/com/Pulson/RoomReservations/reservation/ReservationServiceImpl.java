package com.Pulson.RoomReservations.reservation;

import com.Pulson.RoomReservations.user.User;
import com.Pulson.RoomReservations.user.UserNotFoundException;
import com.Pulson.RoomReservations.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @PersistenceContext
    private EntityManager em;

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Reservation> getActive() {
        LocalDateTime todayDate = LocalDateTime.now();
        return reservationRepository.findAllByStartTimeBetween(LocalDateTime.of(todayDate.getYear(), todayDate.getMonth(), todayDate.getDayOfMonth(), 0, 0),
                LocalDateTime.of(todayDate.getYear(), todayDate.getMonth(), todayDate.getDayOfMonth(), 23, 59));
    }

    @Override
    public List<Reservation> getActiveByUser(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        List<Reservation> activeReservations = new ArrayList<>();
        reservationRepository.findAllByUser(user).forEach(reservation -> {
            if (reservation.getStartTime().isAfter(LocalDateTime.now())) {
                activeReservations.add(reservation);
            }
        });
        return activeReservations;
    }

    @Override
    public List<Reservation> getHistoryByUser(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        List<Reservation> reservationsHistory = new ArrayList<>();
        reservationRepository.findAllByUser(user).forEach(reservation -> {
            if (reservation.getEndTime().isBefore(LocalDateTime.now())) {
                reservationsHistory.add(reservation);
            }
        });
        return reservationsHistory;
    }

    @Override
    public List<Reservation> getByDate(int year, int month, int day) {
        return reservationRepository.findAllByStartTimeBetween
                (LocalDateTime.of(year, month, day, 0, 0),
                 LocalDateTime.of(year, month, day, 23, 59));
    }

    @Override
    public List<Integer> getStartingHoursListByDateByRoom(int year, int month, int day, long roomId) {
        List<Reservation> todayReservations = reservationRepository.findAllByStartTimeBetweenAndRoom_Id
                (LocalDateTime.of(year, month, day, 0, 0),
                 LocalDateTime.of(year, month, day, 23, 59), roomId);
        List<Integer> todayReservationsStartingHours = new ArrayList<>();
        todayReservations.forEach(reservation -> todayReservationsStartingHours.add(reservation.getStartTime().getHour()));
        return todayReservationsStartingHours;
    }

    @Override
    public Integer getAmountByDateByUser(int year, int month, int day, long userId) {
        User user = userRepository.findById(userId).orElseThrow(()
                -> new UserNotFoundException(userId));
        return reservationRepository.countAllByStartTimeBetweenAndUser
                (LocalDateTime.of(year, month, day, 0, 0),
                 LocalDateTime.of(year, month, day, 23, 59), user);
    }

    @Override
    @Transactional
    public String create(Reservation reservation) {
        reservationRepository.save(reservation);
        return "Reservation created";
    }

    @Override
    @Transactional
    public String delete(long id) {
        reservationRepository.deleteById(id);
        return "Reservation deleted";
    }
}
