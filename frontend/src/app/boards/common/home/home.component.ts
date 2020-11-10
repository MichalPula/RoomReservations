import { Component, OnInit } from '@angular/core';
import {ReservationRead, CommonService} from '../../../services/common.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenStorageService} from '../../../services/token-storage.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

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

  public markedActiveReservations: MarkedReservation[] = [];

  isAdmin: boolean;

  pickedReservationId: number;

  constructor(private commonService: CommonService, private route: ActivatedRoute, private router: Router,
              private tokenStorageService: TokenStorageService, private modalService: NgbModal) {
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
    this.isAdmin = this.tokenStorageService.isAdmin();
  }

  private fetchData() {
    this.commonService.getActiveReservations().subscribe(data => {
      this.markedActiveReservations = data as MarkedReservation[];
    });
  }

  openConfirmCancelReservationModal(content, reservationId) {
    this.pickedReservationId = reservationId;
    this.modalService.open(content, { centered: true });
  }

  cancelReservation() {
    this.commonService.cancelReservation(this.pickedReservationId).subscribe(data => {
      this.fetchData();
    });
  }


  changePage(newPage: number) {
    this.router.navigate(['/reservations/active/'], {queryParams: {page: newPage}});
  }
}
