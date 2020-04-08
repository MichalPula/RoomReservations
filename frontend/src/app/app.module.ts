import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';

import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';

import { AuthenticationInterceptorService } from './services/authentication-interceptor.service';
import { RoomsComponent } from './boards/admin/rooms/rooms.component';
import { ReservationAddComponent } from './boards/common/reservation-add/reservation-add.component';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { NgxPaginationModule } from 'ngx-pagination';
import { MyReservationsComponent } from './boards/common/my-reservations/my-reservations.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDialogModule} from '@angular/material/dialog';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { StatisticsComponent } from './profile/statistics/statistics.component';
import {LoginRouteGuardService} from './services/login-route-guard.service';
import {RoomNameValidateDirective} from './boards/admin/rooms/room-name-validator';
import { UsersReservationsByDateComponent } from './boards/admin/users-reservations-by-date/users-reservations-by-date.component';
import {DragDropModule} from '@angular/cdk/drag-drop';
import { AllStudentsComponent } from './boards/admin/all-students/all-students.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    RoomsComponent,
    ReservationAddComponent,
    MyReservationsComponent,
    StatisticsComponent,
    RoomNameValidateDirective,
    UsersReservationsByDateComponent,
    AllStudentsComponent,
  ],
  exports: [ReservationAddComponent],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        ReactiveFormsModule,
        NgbModule,
        NgxPaginationModule,
        MatFormFieldModule,
        MatDialogModule,
        MatButtonModule,
        MatInputModule,
        MatSelectModule,
        BrowserAnimationsModule,
        DragDropModule
    ],
  providers: [AuthenticationInterceptorService, LoginComponent, LoginRouteGuardService, RoomsComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
