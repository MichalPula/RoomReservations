package com.Pulson.RoomReservations.services;

import com.Pulson.RoomReservations.entities.Reservation;
import com.Pulson.RoomReservations.entities.User;
import com.Pulson.RoomReservations.repositories.ReservationRepository;
import com.Pulson.RoomReservations.repositories.UserRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private ReservationRepository reservationRepository;
    private UserRepository userRepository;

    @Autowired
    public StatisticsServiceImpl(ReservationRepository reservationRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }


    @Override
    public String getAmountOfHoursSpentOnParticularActivitiesByUser(long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User " + userId + " NOT found"));
        List<Reservation> reservations = reservationRepository.findAllByUser(user);

        JSONArray array = new JSONArray();
        Map<String, Integer> map = new HashMap<>();

        reservations.forEach(reservation -> {
            String activityName = reservation.getActivity().getName();
            if (map.containsKey(activityName)) {
                map.put(activityName, map.get(activityName) + 1);
            } else {
                map.put(activityName, 1);
            }
        });

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String activityName = entry.getKey();
            Integer amountOfHours = entry.getValue();
            JSONObject json = new JSONObject();
            json.put("y", amountOfHours);
            json.put("activity", activityName);
            array.put(json);
        }
        return array.toString();
    }
}
