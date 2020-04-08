import {Component, OnInit} from '@angular/core';
import {Room, CommonService} from '../../../services/common.service';
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

  roomAddForm: RoomAddUpdateForm = {
    name: '',
    available: null
  };

  roomUpdateForm: RoomAddUpdateForm = {
    name: '',
    available: null
  };

  idOfRoomToUpdate: number;

  constructor(private adminService: AdminService, private userService: CommonService, private modalService: NgbModal) { }

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

  addRoom() {
    this.adminService.addRoom(this.roomAddForm).subscribe(data => {
      this.refreshPage();
    });
  }

  openUpdateRoomModal(content, roomId, roomName, isAvailable) {
    this.idOfRoomToUpdate = roomId;
    this.roomUpdateForm.name = roomName;
    this.modalService.open(content, { centered: true });
  }

  updateRoom() {
    this.adminService.updateRoom(this.idOfRoomToUpdate, this.roomUpdateForm).subscribe(data => {
      this.refreshPage();
    });
  }

  refreshPage() {
    window.location.replace('/rooms');
  }
}
