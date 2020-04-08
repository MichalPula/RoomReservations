import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AdminService, User} from '../../../services/admin.service';

@Component({
  selector: 'app-all-students',
  templateUrl: './all-students.component.html',
  styleUrls: ['./all-students.component.css']
})
export class AllStudentsComponent implements OnInit {

  config: any;
  allStudents: User[];

  constructor(private route: ActivatedRoute, private adminService: AdminService, private router: Router) {
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
    this.adminService.getAllStudents().subscribe(data => {
      this.allStudents = data as User[];
    });
  }

  changePage(newPage: number) {
    this.router.navigate(['/users/students/all'], {queryParams: {page: newPage}});
  }

  getStudentsHistory(studentId: number) {
    this.router.navigate(['/reservations/history/student'], {queryParams: {student: studentId}});
  }

  getStudentsActiveReservations(studentId: number) {
    this.router.navigate(['/reservations/active/student'], {queryParams: {student: studentId}});
  }

  getStudentsStatistics(studentId: number) {
    this.router.navigate(['/statistics/student'], {queryParams: {student: studentId}});
  }
}
