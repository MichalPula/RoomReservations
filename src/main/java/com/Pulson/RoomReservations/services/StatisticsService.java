package com.Pulson.RoomReservations.services;

public interface StatisticsService {
    String getAmountOfHoursSpentOnParticularActivitiesByUser(long userId) throws Exception;
}
