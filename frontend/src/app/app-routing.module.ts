import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { StatisticsComponent } from './profile/statistics/statistics.component';

import { RoomsComponent } from './boards/common/rooms/rooms.component';

import { ReservationAddComponent } from './boards/common/reservation-add/reservation-add.component';
import { MyReservationsComponent } from './boards/common/my-reservations/my-reservations.component';
import { UsersReservationsByDateComponent } from './boards/admin/users-reservations-by-date/users-reservations-by-date.component';

import { LoginRouteGuardService } from './services/login-route-guard.service';
import { AdminRouteGuardService } from './services/admin-route-guard.service';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent, canActivate: [LoginRouteGuardService]},
  { path: 'statistics', component: StatisticsComponent, canActivate: [LoginRouteGuardService]},
  { path: 'rooms', component: RoomsComponent, canActivate: [LoginRouteGuardService, AdminRouteGuardService]},


  { path: 'reservations/active', component:  MyReservationsComponent, canActivate: [LoginRouteGuardService]},
  { path: 'reservations/history', component:  MyReservationsComponent, canActivate: [LoginRouteGuardService]},
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
