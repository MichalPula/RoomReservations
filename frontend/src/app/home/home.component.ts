import { Component, OnInit } from '@angular/core';
import {ReservationRead, UserService} from '../services/user.service';
import {ActivatedRoute, Router} from '@angular/router';

export interface MarkedReservation {
  reservation: ReservationRead;
  color: string;
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public config: any;
  public activeReservations: ReservationRead[];

  public markedActiveReservations: MarkedReservation[] = [];

  constructor(private userService: UserService, private route: ActivatedRoute, private router: Router) {
    this.config =  {
      currentPage: 1,
      itemsPerPage: 7,
      totalItems: 0
    };
    route.queryParams.subscribe(
      params => this.config.currentPage = params.page ? params.page : 1);
  }

  ngOnInit(): void {
    this.fetchData();
  }

  private fetchData() {
    const todayDate = new Date();
    let startingHourNumber = 0;
    let color = '';
    this.userService.getActiveReservations().subscribe(data => {
      this.activeReservations = data as ReservationRead[];
      this.activeReservations.forEach(reservation => {
        const startingHourString = reservation.startTime.substring(11, 13);
        if (startingHourString === '09') {
            startingHourNumber = 9;
        } else {
          startingHourNumber = +startingHourString;
        }
        if (startingHourNumber >= todayDate.getHours()) {
          color = 'green';
        } else {
          color = 'red';
        }
        const markedReservation: MarkedReservation = {
          reservation,
          color
        };
        this.markedActiveReservations.push(markedReservation);
      });
    });
  }

  changePage(newPage: number) {
    this.router.navigate(['/reservations/active/'], {queryParams: {page: newPage}});
  }

  private markReservationsToPastAndFuture() {

  }
}
