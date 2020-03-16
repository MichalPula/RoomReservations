import { Component, OnInit } from '@angular/core';
import {ReservationRead, UserService} from '../services/user.service';
import {TokenStorageService} from '../services/token-storage.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public config: any;
  public activeReservations: ReservationRead[];

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
    this.userService.getActiveReservations().subscribe(data => {
      this.activeReservations = data as ReservationRead[];
    });
  }

  changePage(newPage: number) {
    this.router.navigate(['/reservations/active/'], {queryParams: {page: newPage}});
  }

}
