import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {BasicDataChangeForm, EmailChangeForm, PasswordChangeForm} from '../boards/common/profile/profile.component';

const API_URL = 'http://localhost:8080/';

export class Room {
  id: number;
  name: string;
  available: boolean;

  constructor(id?: number, name?: string, available?: boolean) {
    this.id = id;
    this.name = name;
    this.available = available;
  }
}

export class ReservationAddUpdate {
  userId: number;
  roomName: string;
  startTime: string;
  endTime: string;
  activityName: string;

  constructor(userId?: number, roomName?: string, startTime?: string, endTime?: string, activityName?: string) {
    this.userId = userId;
    this.roomName = roomName;
    this.startTime = startTime;
    this.endTime = endTime;
    this.activityName = activityName;
  }
}

export class ReservationRead {
  id: number;
  firstName: string;
  lastName: string;
  roomName: string;
  startTime: string;
  endTime: string;
  activityName: string;

  constructor(id?: number, firstName?: string, lastName?: string, roomName?: string,
              startTime?: string, endTime?: string, activityName?: string) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.roomName = roomName;
    this.startTime = startTime;
    this.endTime = endTime;
    this.activityName = activityName;
  }
}

export class Activity {
  id: number;
  name: string;
  authorities: string[];
  available: boolean;

  constructor(id?: number, name?: string, authorities?: string[], available?: boolean) {
    this.id = id;
    this.name = name;
    this.authorities = authorities;
    this.available = available;
  }
}


@Injectable({
  providedIn: 'root'
})
export class CommonService {

  constructor(private http: HttpClient) { }

  getAllRooms(): Observable<any> {
    return this.http.get(API_URL + 'rooms/all');
  }

  getAllActivities(): Observable<any> {
    return this.http.get(API_URL + 'activities/all');
  }

  addReservation(reservation: ReservationAddUpdate) {
    return this.http.post(API_URL + 'reservations/add', reservation);
  }
  getActiveReservations(): Observable<any> {
    return this.http.get(API_URL + 'reservations/active');
  }
  getUsersActiveReservations(userId: number): Observable<any> {
    return this.http.get(API_URL + 'reservations/active/' + userId);
  }
  getUsersReservationsHistory(userId: number): Observable<any> {
    return this.http.get(API_URL + 'reservations/history/' + userId);
  }
  getReservationsStartingHoursByPickedDateByRoom(year: number, month: number, day: number, roomId: number): Observable<any> {
    return this.http.get(API_URL + 'reservations/date/' + year + '/' + month + '/' + day + '/room/' + roomId);
  }
  getAmountOfReservationsByPickedDateByUser(year: number, month: number, day: number, userId: number): Observable<any> {
    return this.http.get(API_URL + 'reservations/date/' + year + '/' + month + '/' + day + '/user/' + userId);
  }
  cancelReservation(reservationId: number) {
    return this.http.delete(API_URL + 'reservations/delete/' + reservationId);
  }

  getAmountOfHoursSpentOnParticularActivitiesByUser(userId: number) {
    return this.http.get(API_URL + 'statistics/timeByActivity/' + userId);
  }
  getAmountOfHoursSpentByMonthByUser(userId: number) {
    return this.http.get(API_URL + 'statistics/timeByMonth/' + userId);
  }
  getAverageHoursSpentInRoomsPerUser() {
    return this.http.get(API_URL + 'statistics/averageHoursSpentInRoomsPerUser');
  }

  getBasicAccountData(userId: number) {
    return this.http.get(API_URL + 'users/getBasicAccountData/' + userId);
  }
  updateBasicAccountData(basicDataChangeForm: BasicDataChangeForm) {
    return this.http.put(API_URL + 'users/update/basicInfo', basicDataChangeForm);
  }
  updateEmail(emailChangeForm: EmailChangeForm) {
    return this.http.put(API_URL + 'users/update/email', emailChangeForm);
  }
  updatePassword(passwordChangeForm: PasswordChangeForm) {
    return this.http.put(API_URL + 'users/update/password', passwordChangeForm);
  }


}
