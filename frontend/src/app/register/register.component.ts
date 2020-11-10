import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {AuthenticationService} from '../services/authentication.service';
import {TokenStorageService} from '../services/token-storage.service';
import {LoginComponent} from '../login/login.component';
import {Router} from '@angular/router';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

export interface RegisterForm {
  firstName: string;
  lastName: string;
  phoneNumber: number;
  username: string;
  password: string;
  roles: string[];
}

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  @ViewChild('registerSuccessModal') registerSuccessModal: TemplateRef<any>;

  registerForm: RegisterForm = {
    firstName: '',
    lastName: '',
    phoneNumber: null,
    username: '',
    password: '',
    roles: [],
  };
  isRegistrationFailed: boolean;
  addingAdmin: boolean;
  message = '';

  constructor(private authenticationService: AuthenticationService, private tokenStorageService: TokenStorageService,
              private loginComponent: LoginComponent, private router: Router, private modalService: NgbModal) { }

  ngOnInit(): void {
    {{ this.router.url === '/users/add/admin' ? this.addingAdmin = true : this.addingAdmin = false; }}
    if (this.tokenStorageService.isLoggedIn() && !this.addingAdmin) {
      this.redirectToHomePage();
    }
  }

  registerSubmit() {
    if (this.addingAdmin) {
      this.registerForm.roles.push('admin');
    }
    this.authenticationService.register(this.registerForm).subscribe(
      data => {
        console.log();
      },
      error => {
        this.handleRegistration(error);
      }
    );
  }

  handleRegistration(error) {
    if (error.status === 409) {
      this.isRegistrationFailed = true;
      this.message = 'Email is already taken!';
    }
    if (error.status === 200) {
      this.message = 'Registration successful.';
      this.openSuccessModal();
    }
  }

  openSuccessModal() {
    this.modalService.open(this.registerSuccessModal, { centered: true });
  }

  redirectToHomePage() {
    window.location.replace('/home');
  }

  automaticLoginAfterRegistration() {
    this.loginComponent.form.username = this.registerForm.username;
    this.loginComponent.form.password = this.registerForm.password;
    this.loginComponent.onSubmit();
  }
}
