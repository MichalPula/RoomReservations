import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {RoomAddUpdateForm} from '../boards/admin/rooms/rooms.component';
import {Observable} from 'rxjs';
import {CommonService, Room} from './common.service';
import {ActivityAddUpdateForm} from '../boards/admin/activities/activities.component';

const API_URL = 'http://localhost:8080/';

export interface User {
  id: number;
  firstName: string;
  lastName: string;
  phoneNumber: number;
  username: string;
}

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient, private commonService: CommonService) { }

  addRoom(room: RoomAddUpdateForm) {
    return this.http.post(API_URL + 'rooms/add', room);
  }
  updateRoom(roomId, room: RoomAddUpdateForm) {
    return this.http.put(API_URL + 'rooms/update/' + roomId, room);
  }
  deactivateRoom(roomId: number) {
    return this.http.delete(API_URL + 'rooms/deactivate/' + roomId);
  }
  getRoomsNames(): string[] {
    let rooms: Room[] = [];
    const roomsNames: string[] = [];
    this.commonService.getAllRooms().subscribe(data => {
      rooms = data as Room[];
      rooms.forEach(room => {
        roomsNames.push(room.name);
      });
    });
    return roomsNames;
  }

  getReservationsByPickedDate(year: number, month: number, day: number): Observable<any> {
    return this.http.get(API_URL + 'reservations/date/' + year + '/' + month + '/' + day);
  }

  getAllStudents(): Observable<any> {
    return this.http.get(API_URL + 'users/all/students');
  }
  getStudentByName(firstName: string, lastName: string): Observable<any> {
    return this.http.get(API_URL + 'users/students/name/' + firstName + '/' + lastName);
  }

  getHoursSpentInRooms(): Observable<any> {
    return this.http.get(API_URL + 'statistics/all/rooms');
  }
  getHoursSpentOnActivities(): Observable<any> {
    return this.http.get(API_URL + 'statistics/all/activities');
  }

  addActivity(room: RoomAddUpdateForm) {
    return this.http.post(API_URL + 'activities/add', room);
  }
  updateActivity(activityId, activity: ActivityAddUpdateForm) {
    return this.http.put(API_URL + 'activities/update/' + activityId, activity);
  }
  deactivateActivity(activityId: number) {
    return this.http.delete(API_URL + 'activities/deactivate/' + activityId);
  }

  getAllRoles(): Observable<any> {
    return this.http.get(API_URL + 'roles/all');
  }
}
