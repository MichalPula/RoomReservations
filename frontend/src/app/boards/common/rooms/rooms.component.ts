import { Component, OnInit } from '@angular/core';
import {Room, UserService} from '../../../services/user.service';

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.css']
})

export class RoomsComponent implements OnInit {

  public rooms: Room[];

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.fetchData();
  }

  fetchData() {
    this.userService.getAllRooms().subscribe(data => {
      this.rooms = data as Room[];
    });
  }

  deactivateRoom(roomId: number) {
    this.userService.deactivateRoom(roomId).subscribe(data => {
      this.fetchData();
    });
  }
}
