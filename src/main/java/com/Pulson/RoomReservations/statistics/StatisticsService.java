package com.Pulson.RoomReservations.statistics;

import java.util.List;

public interface StatisticsService {
    String getAmountOfHoursSpentOnParticularActivitiesByUser(long userId) throws Exception;

    String getAmountOfHoursSpentInRoomsByMonth(long userId) throws Exception;

    String getAverageHoursSpentInRoomsPerUser() throws Exception;

    List<HoursPerRoomStatistics> getHoursSpentInRooms() throws Exception;

    List<HoursPerActivityStatistics> getHoursSpentOnActivities() throws Exception;
}
