import {Component, OnInit} from '@angular/core';
import {Room, UserService} from '../../../services/user.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {AdminService} from '../../../services/admin.service';
import {NG_VALIDATORS} from '@angular/forms';
import {RoomNameValidateDirective} from './room-name-validator';

export interface RoomAddUpdateForm {
  name: string;
  available: boolean;
}

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.css'],
  providers: [{
    provide: NG_VALIDATORS,
    useExisting: RoomNameValidateDirective,
    multi: true
  }]
})

export class RoomsComponent implements OnInit {

  rooms: Room[];

  roomAddUpdateForm: RoomAddUpdateForm = {
    name: '',
    available: null
  };

  constructor(private adminService: AdminService, private userService: UserService, private modalService: NgbModal) { }

  ngOnInit() {
    this.fetchData();
  }

  fetchData() {
    this.adminService.getAllRooms().subscribe(data => {
      this.rooms = data as Room[];
    });
  }

  deactivateRoom(roomId: number) {
    this.adminService.deactivateRoom(roomId).subscribe(data => {
      this.fetchData();
    });
  }

  openAddRoomModal(content) {
    this.modalService.open(content, { centered: true });
  }

  newRoomFromSubmit() {
    this.adminService.addRoom(this.roomAddUpdateForm).subscribe(data => {
      this.refreshPage();
    });
  }

  refreshPage() {
    window.location.replace('/rooms');
  }
}
