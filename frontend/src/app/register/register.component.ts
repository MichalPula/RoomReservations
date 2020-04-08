import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../services/authentication.service';
import {TokenStorageService} from '../services/token-storage.service';
import {LoginComponent} from '../login/login.component';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: any = {};
  isRegistrationSuccessful = false;
  isRegistrationFailed = false;
  errorMessage = '';

  constructor(private authenticationService: AuthenticationService, private tokenStorageService: TokenStorageService,
              private loginComponent: LoginComponent) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.authenticationService.register(this.form).subscribe(
      data => {
        this.isRegistrationFailed = false;
        this.isRegistrationSuccessful = true;

      },
      error => {
        this.errorMessage = error.error.message;
        this.isRegistrationFailed = false;
        console.log(this.errorMessage);
        // throws undefined error with 200 OK code...
        this.automaticLoginAfterRegistration(this.form);
      }
    );
  }

  redirectToHomePage() {
    window.location.replace('/home');
  }

  automaticLoginAfterRegistration(form) {
    this.loginComponent.form.username = this.form.username;
    this.loginComponent.form.password = this.form.password;
    this.loginComponent.onSubmit();
  }
}
