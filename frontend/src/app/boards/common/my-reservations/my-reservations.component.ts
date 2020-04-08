import { Component, OnInit } from '@angular/core';
import {ReservationRead, ReservationAddUpdate, CommonService} from '../../../services/common.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenStorageService} from '../../../services/token-storage.service';
import {MatDialog} from '@angular/material/dialog';

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

  constructor(private userService: CommonService, private tokenStorageService: TokenStorageService,
              private route: ActivatedRoute, private router: Router) {
    this.config =  {
      currentPage: 1,
      itemsPerPage: 7,
      totalItems: 0
    };
    route.queryParams.subscribe(
      params => this.config.currentPage = params.page ? params.page : 1);
  }

  ngOnInit(): void {
    this.getCurrentURL();
    this.fetchData();
  }

  private fetchData() {
    if (this.activeReservations === true) {
      this.userService.getUsersActiveReservations(this.tokenStorageService.getUser().id).subscribe(data => {
        this.reservationsList = data as ReservationRead[];
      });
    }
    if (this.reservationsHistory === true) {
      this.userService.getReservationsHistory(this.tokenStorageService.getUser().id).subscribe(data => {
        this.reservationsList = data as ReservationRead[];
      });
    }
  }

  changePage(newPage: number) {
    if (this.activeReservations === true) {
      this.router.navigate(['/reservations/active/'], {queryParams: {page: newPage}});
    }
    if (this.reservationsHistory === true) {
      this.router.navigate(['/reservations/history/'], {queryParams: {page: newPage}});
    }
  }

  getCurrentURL() {
    if (this.router.url === '/reservations/active') {
      this.reservationsHistory = false;
      this.activeReservations = true;
    }
    if (this.router.url === '/reservations/history') {
      this.reservationsHistory = true;
      this.activeReservations = false;
    }
  }

  cancelReservation(reservationId: number) {
    this.userService.cancelReservation(reservationId).subscribe(data => {
      this.fetchData();
    });
  }
}
