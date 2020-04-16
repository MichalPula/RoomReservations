package com.Pulson.RoomReservations.models.statistics;

import org.hibernate.annotations.NamedNativeQuery;

import javax.persistence.*;

@NamedNativeQuery(
        name = HoursPerActivityStatistics.HOURS_PER_ACTIVITY,
        query = "select a.name as activity, count(*) as total_hours" +
                " from reservations res" +
                " join activities a on a.id = res.activity_id" +
                " join activities_roles a_r on a_r.activity_id = a.id" +
                " join roles r on r.id = a_r.role_id" +
                " where start_time >= ?" +
                " and start_time < ?" +
                " and r.role not like 'ROLE_ADMIN'" +
                " group by a.name",
        resultSetMapping = "HoursPerActivityResultMapping")

@SqlResultSetMapping(
        name = "HoursPerActivityResultMapping",
        classes = {
                @ConstructorResult(
                        targetClass = HoursPerActivityStatistics.class,
                        columns = {
                                @ColumnResult(name = "activity", type = String.class),
                                @ColumnResult(name = "total_hours", type = Integer.class)
                        }
                )
        })

@Entity
public class HoursPerActivityStatistics {

    public static final String HOURS_PER_ACTIVITY = "HoursPerActivity";

    @Id
    private String activityName;

    private int amountOfHours;


    public HoursPerActivityStatistics(String activityName, int amountOfHours) {
        this.activityName = activityName;
        this.amountOfHours = amountOfHours;
    }

    public HoursPerActivityStatistics() {
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public int getAmountOfHours() {
        return amountOfHours;
    }

    public void setAmountOfHours(int amountOfHours) {
        this.amountOfHours = amountOfHours;
    }
}
