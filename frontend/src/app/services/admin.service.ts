import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {RoomAddUpdateForm} from '../boards/common/rooms/rooms.component';
import {Observable} from 'rxjs';
import {RoomsComponent} from '../boards/common/rooms/rooms.component';
import {Room} from './common.service';
import {EmailChangeForm} from '../profile/profile.component';

const API_URL = 'http://localhost:8080/';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  getAllRooms(): Observable<any> {
    return this.http.get(API_URL + 'rooms/all');
  }
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
    this.getAllRooms().subscribe(data => {
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
}
