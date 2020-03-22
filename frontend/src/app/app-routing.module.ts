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
import {RouteGuardService} from './services/route-guard.service';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent, canActivate: [RouteGuardService]},
  { path: 'statistics', component: StatisticsComponent, canActivate: [RouteGuardService]},
  { path: 'rooms', component: RoomsComponent, canActivate: [RouteGuardService]},


  { path: 'reservations/active', component:  MyReservationsComponent, canActivate: [RouteGuardService]},
  { path: 'reservations/history', component:  MyReservationsComponent, canActivate: [RouteGuardService]},
  { path: 'reservations/date', component:  MyReservationsComponent, canActivate: [RouteGuardService]},
  { path: 'reservations/add', component:  ReservationAddComponent, canActivate: [RouteGuardService]},

  { path: '', redirectTo: '/login', pathMatch: 'full' }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
