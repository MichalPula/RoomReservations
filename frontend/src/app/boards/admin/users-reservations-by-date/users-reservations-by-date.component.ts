import { Component, OnInit } from '@angular/core';
import {NgbCalendar, NgbDate, NgbDateStruct, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {AdminService} from '../../../services/admin.service';
import {MarkedReservation} from '../../../home/home.component';
import {CommonService} from '../../../services/common.service';

@Component({
  selector: 'app-users-reservations-by-date',
  templateUrl: './users-reservations-by-date.component.html',
  styleUrls: ['./users-reservations-by-date.component.css']
})
export class UsersReservationsByDateComponent implements OnInit {

  reservationDate: NgbDateStruct;
  datesDisabled = false;
  markDisabled: (date: NgbDate) => boolean;
  thisYear: number;
  thisMonth: number;
  thisDay: number;
  minDay: number;
  maxDay: number;
  maxMonth: number;

  pickedReservationId: number;

  public markedReservationsList: MarkedReservation[] = [];

  constructor(private adminService: AdminService, private calendar: NgbCalendar, private commonService: CommonService,
              private modalService: NgbModal) {}

  ngOnInit(): void {
    this.setMaxMinDate();
    this.disableWeekends();
  }

  getReservationsByPickedDate() {
    this.adminService.getReservationsByPickedDate(this.reservationDate.year, this.reservationDate.month, this.reservationDate.day)
      .subscribe(data => {
        this.markedReservationsList = data as MarkedReservation[];
      });
  }

  cancelReservation() {
    this.commonService.cancelReservation(this.pickedReservationId).subscribe(data => {
      this.getReservationsByPickedDate();
    });
  }

  openConfirmCancelReservationModal(content, reservationId) {
    this.pickedReservationId = reservationId;
    this.modalService.open(content, { centered: true });
  }

  private setMaxMinDate() {
    const todayDate = new Date();
    this.thisYear = todayDate.getFullYear();
    this.thisMonth = todayDate.getMonth() + 1;
    this.thisDay = todayDate.getDate();

    if (todayDate.getHours() >= 14) {
      this.minDay = this.thisDay + 1;
    } else {
      this.minDay = this.thisDay;
    }
    this.maxMonth = this.thisMonth;
    this.maxDay = this.thisDay + 7;
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
}
