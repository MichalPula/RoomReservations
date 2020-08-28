package com.Pulson.RoomReservations.statistics;

import com.Pulson.RoomReservations.reservation.Reservation;
import com.Pulson.RoomReservations.reservation.ReservationRepository;
import com.Pulson.RoomReservations.user.User;
import com.Pulson.RoomReservations.user.UserNotFoundException;
import com.Pulson.RoomReservations.user.UserRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final EntityManager entityManager;

    @Autowired
    public StatisticsServiceImpl(ReservationRepository reservationRepository, UserRepository userRepository, EntityManager entityManager) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<HoursPerActivityPerUser> getAmountOfHoursSpentOnParticularActivitiesByUser(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        List<Reservation> reservations = reservationRepository.findAllByUser(user);

        List<HoursPerActivityPerUser> hoursPerActivityPerUsers = new ArrayList<>();
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
            hoursPerActivityPerUsers.add(new HoursPerActivityPerUser(amountOfHours, activityName));
        }
        return hoursPerActivityPerUsers;
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
    public BigDecimal getAverageHoursSpentInRoomsPerUser() {
        Query query = entityManager.createNativeQuery("select avg(total_hours) as total_hours_avg from" +
                " (select count(*) as total_hours" +
                " from reservations" +
                " WHERE start_time >= '2020-01-01'" +
                " AND start_time < '2020-12-31'" +
                " group by user_id) OK");
        return (BigDecimal) query.getSingleResult();
    }

    @Override
    public List<HoursPerRoomStatistics> getHoursSpentInRooms() {
        List<Timestamp> startAndEndTimestamp = getStartAndEndYearDate();

        return Collections.checkedList(
                entityManager.createNamedQuery(HoursPerRoomStatistics.HOURS_PER_ROOM, HoursPerRoomStatistics.class)
                        .setParameter(1, startAndEndTimestamp.get(0))
                        .setParameter(2, startAndEndTimestamp.get(1))
                        .getResultList(), HoursPerRoomStatistics.class);
    }

    @Override
    public List<HoursPerActivityStatistics> getHoursSpentOnActivities() {
        List<Timestamp> startAndEndTimestamp = getStartAndEndYearDate();

        return Collections.checkedList(
                entityManager.createNamedQuery(HoursPerActivityStatistics.HOURS_PER_ACTIVITY, HoursPerActivityStatistics.class)
                        .setParameter(1, startAndEndTimestamp.get(0))
                        .setParameter(2, startAndEndTimestamp.get(1))
                        .getResultList(), HoursPerActivityStatistics.class);
    }

    private List<Timestamp> getStartAndEndYearDate() {
        List<Timestamp> startAndEndYearDate = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Logger logger = LoggerFactory.getLogger(StatisticsServiceImpl.class);
        try {
            Date parsedStartDate = dateFormat.parse(LocalDateTime.now().getYear() + "-01-01");
            Date parsedEndDate = dateFormat.parse(LocalDateTime.now().getYear() + "-12-31");
            startAndEndYearDate.add(new Timestamp(parsedStartDate.getTime()));
            startAndEndYearDate.add(new Timestamp(parsedEndDate.getTime()));
        } catch (ParseException e) {
            logger.error("Date parsing failure!");
        }
        return startAndEndYearDate;
    }
}
