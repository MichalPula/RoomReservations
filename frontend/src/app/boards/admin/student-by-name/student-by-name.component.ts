import { Component, OnInit } from '@angular/core';
import {AdminService, User} from '../../../services/admin.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-student-by-name',
  templateUrl: './student-by-name.component.html',
  styleUrls: ['./student-by-name.component.css']
})
export class StudentByNameComponent implements OnInit {

  searchedName: string;
  foundStudents: User[] = [];
  firstName: string;
  lastName: string;
  beforeFirstSearch = true;

  constructor(private router: Router, private adminService: AdminService) { }

  ngOnInit(): void {
  }

  submitName() {
    this.beforeFirstSearch = false;
    if (this.searchedName.includes(' ')) {
      const studentsFirstAndLastName: string[] = this.searchedName.split(' ');
      this.firstName = studentsFirstAndLastName[0];
      this.lastName = studentsFirstAndLastName[1];
    } else {
      this.firstName = this.searchedName;
      this.lastName = null;
    }
    this.adminService.getStudentByName(this.firstName, this.lastName).subscribe(data => {
      this.foundStudents = data as User[];
    });
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
