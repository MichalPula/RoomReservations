package com.Pulson.RoomReservations.Reservation;

import com.Pulson.RoomReservations.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByUser(User user);

    List<Reservation> findAllByStartTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Reservation> findAllByStartTimeBetweenAndRoom_Id(LocalDateTime startDate, LocalDateTime endDate, long roomId);

    Integer countAllByStartTimeBetweenAndUser(LocalDateTime startDate, LocalDateTime endDate, User user);
}
