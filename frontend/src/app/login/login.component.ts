import {Component, Injectable, OnInit} from '@angular/core';
import {AuthenticationService} from '../services/authentication.service';
import {TokenStorageService} from '../services/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(private authenticationService: AuthenticationService, private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    if (this.tokenStorageService.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorageService.getUser().roles;
    }
  }

  onSubmit() {
    this.authenticationService.login(this.form).subscribe(
      data => {
        this.tokenStorageService.saveToken(data.jwtToken);
        this.tokenStorageService.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorageService.getUser().roles;
        this.redirectToHomePage();
      }, error => {
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  redirectToHomePage() {
    window.location.replace('/home');
  }

  redirectToRegisterPage() {
    window.location.replace('/register');
  }
}
