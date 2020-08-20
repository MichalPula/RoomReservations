package com.Pulson.RoomReservations.Reservation;

public class MarkedReservation {

    private ReservationReadDTO reservation;
    private String color;

    public MarkedReservation(ReservationReadDTO reservation, String color) {
        this.reservation = reservation;
        this.color = color;
    }

    public MarkedReservation() {

    }

    public ReservationReadDTO getReservation() {
        return reservation;
    }

    public void setReservation(ReservationReadDTO reservation) {
        this.reservation = reservation;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
