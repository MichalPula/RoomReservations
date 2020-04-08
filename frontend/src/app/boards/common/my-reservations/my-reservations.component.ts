import { Component, OnInit } from '@angular/core';
import {ReservationRead, ReservationAddUpdate, CommonService} from '../../../services/common.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenStorageService} from '../../../services/token-storage.service';

@Component({
  selector: 'app-my-reservations',
  templateUrl: './my-reservations.component.html',
  styleUrls: ['./my-reservations.component.css']
})
export class MyReservationsComponent implements OnInit {

  public config: any;
  public reservationsList: ReservationRead[];
  public reservationsHistory: boolean;
  public activeReservations: boolean;
  adminIsSearchingStudentsHistory: boolean;
  adminIsSearchingStudentsActiveReservations: boolean;
  searchedStudentId: number;

  constructor(private commonService: CommonService, private tokenStorageService: TokenStorageService,
              private activatedRoute: ActivatedRoute, private router: Router) {
    this.config =  {
      currentPage: 1,
      itemsPerPage: 7,
      totalItems: 0
    };
    activatedRoute.queryParams.subscribe(
      params => this.config.currentPage = params.page ? params.page : 1);
  }

  ngOnInit(): void {
    this.getCurrentURL();
    this.fetchData();
  }

  private getCurrentURL() {
    const urlWithoutParams = this.router.url.split('?')[0];
    switch (urlWithoutParams) {
      case '/reservations/active':
        this.reservationsHistory = false;
        this.activeReservations = true;
        break;
      case '/reservations/history':
        this.reservationsHistory = true;
        this.activeReservations = false;
        break;
      case '/reservations/active/student':
        this.adminIsSearchingStudentsActiveReservations = true;
        break;
      case '/reservations/history/student':
        this.adminIsSearchingStudentsHistory = true;
        break;
    }
  }

  private fetchData() {
    if (this.activeReservations) {
      this.commonService.getUsersActiveReservations(this.tokenStorageService.getUser().id).subscribe(data => {
        this.reservationsList = data as ReservationRead[];
      });
    } else if (this.reservationsHistory) {
      this.commonService.getUsersActiveReservations(this.tokenStorageService.getUser().id).subscribe(data => {
        this.reservationsList = data as ReservationRead[];
      });
    } else if (this.adminIsSearchingStudentsActiveReservations) {
      this.activatedRoute.queryParams.subscribe(params => {
        this.searchedStudentId = params.student;
        this.commonService.getUsersActiveReservations(this.searchedStudentId).subscribe(data => {
          this.reservationsList = data as ReservationRead[];
        });
      });
    } else if (this.adminIsSearchingStudentsHistory) {
      this.activatedRoute.queryParams.subscribe(params => {
        this.searchedStudentId = params.student;
        this.commonService.getUsersReservationsHistory(this.searchedStudentId).subscribe(data => {
          this.reservationsList = data as ReservationRead[];
        });
      });
    }
  }

  changePage(newPage: number) {
    if (this.activeReservations) {
      this.router.navigate(['/reservations/active/'], {queryParams: {page: newPage}});
    } else if (this.reservationsHistory) {
      this.router.navigate(['/reservations/history/'], {queryParams: {page: newPage}});
    } else if (this.adminIsSearchingStudentsActiveReservations) {
      this.router.navigate(['/reservations/active/student'], {queryParams: {student: this.searchedStudentId, page: newPage}});
    } else if (this.adminIsSearchingStudentsHistory) {
      this.router.navigate(['/reservations/history/student'], {queryParams: {student: this.searchedStudentId, page: newPage}});
    }
  }

  cancelReservation(reservationId: number) {
    this.commonService.cancelReservation(reservationId).subscribe(data => {
      this.fetchData();
    });
  }
}
