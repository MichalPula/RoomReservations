import {Component, OnInit} from '@angular/core';
import {Activity, Room, CommonService} from '../../../services/common.service';
import {TokenStorageService} from '../../../services/token-storage.service';
import {NgbCalendar, NgbDate, NgbDatepickerConfig, NgbDateStruct, NgbTimeStruct} from '@ng-bootstrap/ng-bootstrap';

export interface ReservationAddTimeHelper {
  startTime: string;
  endTime: string;
  viewValue: string;
  hourStartNumber: number;
}

@Component({
  selector: 'app-reservation-add',
  templateUrl: './reservation-add.component.html',
  styleUrls: ['./reservation-add.component.css']
})

export class ReservationAddComponent implements OnInit {

  public roles: string[];

  public activities: Activity[];
  public rooms: Room[];
  public todayReservationsStartingHours: number[];
  public amountOfReservationsByPickedDateByUser: number;
  public maxAmountOfReservationsPerUser: number;

  errorMessage: string;

  pickedRoomName: string;
  reservationDate: NgbDateStruct;
  reservationAddForm: any = {};

  thisYear: number;
  thisMonth: number;
  thisDay: number;
  thisHour: number;
  minDay: number;
  maxDay: number;
  maxMonth: number;

  datesDisabled = false;
  markDisabled: (date: NgbDate) => boolean;

  userSlots: ReservationAddTimeHelper[] = [
    {startTime: '09:00', endTime: '10:00', viewValue: '9:00 - 10:00', hourStartNumber: 9},
    {startTime: '10:00', endTime: '11:00', viewValue: '10:00 - 11:00', hourStartNumber: 10},
    {startTime: '11:00', endTime: '12:00', viewValue: '11:00 - 12:00', hourStartNumber: 11},
    {startTime: '12:00', endTime: '13:00', viewValue: '12:00 - 13:00', hourStartNumber: 12},
    {startTime: '13:00', endTime: '14:00', viewValue: '13:00 - 14:00', hourStartNumber: 13},
    {startTime: '14:00', endTime: '15:00', viewValue: '14:00 - 15:00', hourStartNumber: 14}
  ];

  selectedTimeRange: string;
  selectedHourStartNumber: number;

  constructor(private commonService: CommonService, private tokenStorageService: TokenStorageService,
              private calendar: NgbCalendar) { }

  ngOnInit(): void {
    this.fetchData();
    this.setMaxMinDate();
    this.disableWeekends();
    this.setMaxAmountOfReservations();
  }

  onSubmit() {
    this.reservationAddForm.userId = this.tokenStorageService.getUser().id;
    this.reservationAddForm.roomName = this.pickedRoomName;
    this.reservationAddForm.startTime = this.parseDateAndTime(this.selectedTimeRange, true);
    this.reservationAddForm.endTime = this.parseDateAndTime(this.selectedTimeRange, false);

    this.commonService.addReservation(this.reservationAddForm).subscribe(
       data => {
         this.redirectToMyReservations();
       }, error => {
         this.errorMessage = error.error.message;
       }
    );
  }

  fetchData() {
    this.commonService.getAllActivities().subscribe(data => {
      this.activities = data as Activity[];
      if (this.isAdmin() === false) {
         for (const activity of this.activities) {
            if (activity.authorities.includes('ROLE_USER') &&
             activity.available) {
              continue;
            }
            if (activity.authorities.includes('ROLE_ADMIN') || activity.available === false) {
              this.activities = this.activities.filter(obj => obj !== activity);
            }
         }
      }
    });

    this.commonService.getAllRooms().subscribe(data => {
      this.rooms = data as Room[];
      for (const room of this.rooms) {
        if (room.available === false) {
          this.rooms = this.rooms.filter(obj => obj !== room);
        }
      }
    });
  }

  private setMaxMinDate() {
    const todayDate = new Date();
    this.thisYear = todayDate.getFullYear();
    this.thisMonth = todayDate.getMonth() + 1;
    this.thisDay = todayDate.getDate();
    this.thisHour = todayDate.getHours();

    if (todayDate.getHours() >= 14) {
      this.minDay = this.thisDay + 1;
    } else {
      this.minDay = this.thisDay;
    }

    if (this.isAdmin() === true) {
      this.maxMonth = this.thisMonth + 1;
    } else {
      this.maxDay = this.thisDay + 7;
      this.maxMonth = this.thisMonth;
    }
  }

  disableWeekends() {
    if (this.datesDisabled) {
      this.datesDisabled = false;
      this.markDisabled = (date: NgbDate) => false;
    } else {
      this.datesDisabled = true;
      this.markDisabled = (date: NgbDate) => this.calendar.getWeekday(date) >= 6;
    }
  }

  private setMaxAmountOfReservations() {
    if (this.isAdmin() === true) {
      this.maxAmountOfReservationsPerUser = 6;
    } else {
      this.maxAmountOfReservationsPerUser = 3;
    }
  }

  private parseDateAndTime(timeRange: string, start: boolean) {
    const reservationDate = new Date(this.reservationDate.year, this.reservationDate.month - 1, this.reservationDate.day + 1)
      .toISOString().substring(0, 10);
    if (start === true) {
      return  reservationDate + ' ' + timeRange.substring(0, 5);
    } else {
      return  reservationDate + ' ' + timeRange.substring(6, 11);
    }
  }

  public isAdmin() {
    this.roles = this.tokenStorageService.getUser().roles;
    return this.roles.includes('ROLE_ADMIN');
  }

  redirectToMyReservations() {
    window.location.replace('/reservations/active');
  }

  getAmountOfReservationsByPickedDateByUser() {
    this.commonService.getAmountOfReservationsByPickedDateByUser(this.reservationDate.year, this.reservationDate.month,
      this.reservationDate.day, this.tokenStorageService.getUser().id)
      .subscribe(data => {
        this.amountOfReservationsByPickedDateByUser = data as number;
      });
  }

  getReservationsStartingHoursByPickedDateByRoom() {
    if (this.pickedRoomName != null) {
      this.commonService.getReservationsStartingHoursByPickedDateByRoom(this.reservationDate.year,
        this.reservationDate.month, this.reservationDate.day, this.getRoomIdByName(this.pickedRoomName))
        .subscribe(data => {
          this.todayReservationsStartingHours = data as number[];
        });
    }
  }

  private getRoomIdByName(roomName: string): number {
    let roomId = null;
    this.rooms.forEach(room => {
      if (room.name === roomName) {
        roomId = room.id;
      }
    });
    return roomId;
  }
}
