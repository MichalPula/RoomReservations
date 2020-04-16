package com.Pulson.RoomReservations.models.statistics;

import org.hibernate.annotations.NamedNativeQuery;

import javax.persistence.*;

@NamedNativeQuery(
        name = HoursPerRoomStatistics.HOURS_PER_ROOM,
        query = "select r.name as room, count(*) as total_hours" +
                " from reservations res" +
                " join rooms r on r.id = res.room_id" +
                " join activities a on a.id = res.activity_id" +
                " join activities_roles a_r on a_r.activity_id = a.id" +
                " join roles on roles.id = a_r.role_id" +
                " where start_time >= ?" +
                " and start_time < ?" +
                " and roles.role not like 'ROLE_ADMIN'" +
                " group by r.name",
        resultSetMapping = "HoursPerRoomResultMapping")

@SqlResultSetMapping(
        name = "HoursPerRoomResultMapping",
        classes = {
                @ConstructorResult(
                        targetClass = HoursPerRoomStatistics.class,
                        columns = {
                                @ColumnResult(name = "room", type = String.class),
                                @ColumnResult(name = "total_hours", type = Integer.class)
                        }
                )
        })

@Entity
public class HoursPerRoomStatistics {

    public static final String HOURS_PER_ROOM = "HoursPerRoom";

    @Id
    private String roomName;

    private int amountOfHours;


    public HoursPerRoomStatistics(String roomName, int amountOfHours) {
        this.roomName = roomName;
        this.amountOfHours = amountOfHours;
    }

    public HoursPerRoomStatistics() {
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getAmountOfHours() {
        return amountOfHours;
    }

    public void setAmountOfHours(int amountOfHours) {
        this.amountOfHours = amountOfHours;
    }
}
