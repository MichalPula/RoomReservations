import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

import { HomeComponent } from './boards/common/home/home.component';
import { ProfileComponent } from './boards/common/profile/profile.component';
import { StatisticsComponent } from './boards/common/profile/statistics/statistics.component';

import { RoomsComponent } from './boards/admin/rooms/rooms.component';

import { ReservationAddComponent } from './boards/common/reservation-add/reservation-add.component';
import { MyReservationsComponent } from './boards/common/my-reservations/my-reservations.component';
import { UsersReservationsByDateComponent } from './boards/admin/users-reservations-by-date/users-reservations-by-date.component';
import { AllStudentsComponent } from "./boards/admin/all-students/all-students.component";
import { StudentByNameComponent } from './boards/admin/student-by-name/student-by-name.component';
import { RoomsAndStudentsStatisticsComponent } from './boards/admin/rooms-and-students-statistics/rooms-and-students-statistics.component';

import { LoginRouteGuardService } from './services/login-route-guard.service';
import { AdminRouteGuardService } from './services/admin-route-guard.service';
import {ActivitiesComponent} from './boards/admin/activities/activities.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent, canActivate: [LoginRouteGuardService]},
  { path: 'statistics', component: StatisticsComponent, canActivate: [LoginRouteGuardService]},
  { path: 'rooms', component: RoomsComponent, canActivate: [LoginRouteGuardService, AdminRouteGuardService]},
  { path: 'activities', component: ActivitiesComponent, canActivate: [LoginRouteGuardService, AdminRouteGuardService]},

  { path: 'users/add/admin', component: RegisterComponent, canActivate: [LoginRouteGuardService, AdminRouteGuardService]},
  { path: 'users/students/all', component: AllStudentsComponent, canActivate: [LoginRouteGuardService, AdminRouteGuardService]},
  { path: 'users/students/search', component: StudentByNameComponent, canActivate: [LoginRouteGuardService, AdminRouteGuardService]},

  { path: 'reservations/active', component:  MyReservationsComponent, canActivate: [LoginRouteGuardService]},
  { path: 'reservations/history', component:  MyReservationsComponent, canActivate: [LoginRouteGuardService]},

  { path: 'reservations/history/student', component:  MyReservationsComponent, canActivate: [LoginRouteGuardService, AdminRouteGuardService]},
  { path: 'reservations/active/student', component:  MyReservationsComponent, canActivate: [LoginRouteGuardService, AdminRouteGuardService]},
  { path: 'statistics/student', component:  StatisticsComponent, canActivate: [LoginRouteGuardService, AdminRouteGuardService]},

  { path: 'statistics/all', component:  RoomsAndStudentsStatisticsComponent, canActivate: [LoginRouteGuardService, AdminRouteGuardService]},

  { path: 'reservations/date', component:  MyReservationsComponent, canActivate: [LoginRouteGuardService]},
  { path: 'reservations/add', component:  ReservationAddComponent, canActivate: [LoginRouteGuardService]},
  { path: 'reservations/all/date', component:  UsersReservationsByDateComponent, canActivate: [LoginRouteGuardService, AdminRouteGuardService]},

  { path: '', redirectTo: '/login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
