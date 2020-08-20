package com.Pulson.RoomReservations.services;

import com.Pulson.RoomReservations.entities.Reservation;
import com.Pulson.RoomReservations.User.User;
import com.Pulson.RoomReservations.models.statistics.HoursPerActivityStatistics;
import com.Pulson.RoomReservations.models.statistics.HoursPerRoomStatistics;
import com.Pulson.RoomReservations.repositories.ReservationRepository;
import com.Pulson.RoomReservations.User.UserRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private ReservationRepository reservationRepository;
    private UserRepository userRepository;
    private EntityManager entityManager;

    @Autowired
    public StatisticsServiceImpl(ReservationRepository reservationRepository, UserRepository userRepository, EntityManager entityManager) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.entityManager = entityManager;
    }

    @Override
    public String getAmountOfHoursSpentOnParticularActivitiesByUser(long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User " + userId + " NOT found"));
        List<Reservation> reservations = reservationRepository.findAllByUser(user);

        JSONArray objectsArray = new JSONArray();
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
            objectsArray.put(json);
        }
        return objectsArray.toString();
    }

    @Override
    public String getAmountOfHoursSpentInRoomsByMonth(long userId) throws Exception {
        Query query = entityManager.createNativeQuery("select" +
                " date_trunc('month', start_time)," +
                " count(1) as hours" +
                " from reservations" +
                " where user_id = ?" +
                " AND start_time >= ?" +
                " AND start_time < ?" +
                " group by 1");

        List<Timestamp> startAndEndTimestamp = getStartAndEndYearDate();
        query.setParameter(1, userId);
        query.setParameter(2, startAndEndTimestamp.get(0));
        query.setParameter(3, startAndEndTimestamp.get(1));

        JSONArray objectsArray = new JSONArray();
        List<Object[]> resultList = query.getResultList();

        for (int i = 0; i <= 11; i++) {
            JSONObject json = new JSONObject();
            if (i < resultList.size()) {
                Object[] record = resultList.get(i);
                json.put("hours", record[1]);
            } else {
                json.put("hours", 0);
            }
            objectsArray.put(json);
        }

        return objectsArray.toString();
    }

    @Override
    public String getAverageHoursSpentInRoomsPerUser() throws Exception {
        Query query = entityManager.createNativeQuery("select avg(total_hours) as total_hours_avg from" +
                " (select count(*) as total_hours" +
                " from reservations" +
                " WHERE start_time >= '2020-01-01'" +
                " AND start_time < '2020-12-31'" +
                " group by user_id) OK");
        Object result = query.getSingleResult();
        return String.valueOf(result);
    }

    @Override
    public List<HoursPerRoomStatistics> getHoursSpentInRooms() throws Exception {
        List<Timestamp> startAndEndTimestamp = getStartAndEndYearDate();

        return Collections.checkedList(
                entityManager.createNamedQuery(HoursPerRoomStatistics.HOURS_PER_ROOM, HoursPerRoomStatistics.class)
                        .setParameter(1, startAndEndTimestamp.get(0))
                        .setParameter(2, startAndEndTimestamp.get(1))
                        .getResultList(), HoursPerRoomStatistics.class);
    }

    @Override
    public List<HoursPerActivityStatistics> getHoursSpentOnActivities() throws Exception {
        List<Timestamp> startAndEndTimestamp = getStartAndEndYearDate();

        return Collections.checkedList(
                entityManager.createNamedQuery(HoursPerActivityStatistics.HOURS_PER_ACTIVITY, HoursPerActivityStatistics.class)
                        .setParameter(1, startAndEndTimestamp.get(0))
                        .setParameter(2, startAndEndTimestamp.get(1))
                        .getResultList(), HoursPerActivityStatistics.class);
    }

    private List<Timestamp> getStartAndEndYearDate() throws ParseException {
        List<Timestamp> startAndEndYearDate = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedStartDate = dateFormat.parse(LocalDateTime.now().getYear() + "-01-01");
        Date parsedEndDate = dateFormat.parse(LocalDateTime.now().getYear() + "-12-31");
        startAndEndYearDate.add(new Timestamp(parsedStartDate.getTime()));
        startAndEndYearDate.add(new Timestamp(parsedEndDate.getTime()));
        return startAndEndYearDate;
    }
}
