package com.Pulson.RoomReservations.statistics;

import java.math.BigDecimal;
import java.util.List;

public interface StatisticsService {
    List<HoursPerActivityPerUser> getAmountOfHoursSpentOnParticularActivitiesByUser(long userId);

    List<HoursPerMonthPerUser> getAmountOfHoursSpentInRoomsByMonth(long userId);

    BigDecimal getAverageHoursSpentInRoomsPerUser();

    List<HoursPerRoomStatistics> getHoursSpentInRooms();

    List<HoursPerActivityStatistics> getHoursSpentOnActivities();
}
