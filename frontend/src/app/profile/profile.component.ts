import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {TokenStorageService} from '../services/token-storage.service';
import {CommonService} from '../services/common.service';

export interface BasicDataChangeForm {
  userId: null;
  firstName: string;
  lastName: string;
  phoneNumber: number;
}

export interface EmailChangeForm {
  userId: null;
  email: string;
}

export interface PasswordChangeForm {
  userId: null;
  oldPassword: string;
  newPassword: string;
}

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  @ViewChild('successModal') successModal: TemplateRef<any>;

  basicDataChangeForm: BasicDataChangeForm = {
    userId: null,
    firstName: '',
    lastName: '',
    phoneNumber: null
  };

  emailChangeForm: EmailChangeForm = {
    userId: null,
    email: ''
  };

  passwordChangeForm: PasswordChangeForm = {
    userId: null,
    oldPassword: '',
    newPassword: ''
  };
  newPasswordRepeat: '';

  showPasswordChangeErrorMessage: boolean;
  showEmailChangeErrorMessage: boolean;
  message: string;

  constructor(private modalService: NgbModal, private tokenStorageService: TokenStorageService,
              private userService: CommonService) { }

  ngOnInit(): void {
    this.fetchData();
  }

  fetchData() {
    this.getBasicAccoutData();
    this.emailChangeForm.email = this.tokenStorageService.getUser().username;
  }

  getBasicAccoutData() {
    this.userService.getBasicAccountData(this.tokenStorageService.getUser().id).subscribe(data => {
      this.basicDataChangeForm = data as BasicDataChangeForm;
    });
  }

  openPasswordChangeModal(content) {
    this.modalService.open(content, { centered: true });
  }

  openEmailChangeModal(content) {
    this.modalService.open(content, { centered: true });
  }

  openSuccessModal() {
    this.modalService.open(this.successModal, { centered: true });
  }

  basicDataSubmit() {
    this.basicDataChangeForm.userId = this.tokenStorageService.getUser().id;
    this.userService.updateBasicAccountData(this.basicDataChangeForm)
        .subscribe(data => {
      this.refreshPage();
    });
  }

  passwordSubmit() {
    this.passwordChangeForm.userId = this.tokenStorageService.getUser().id;
    this.userService.updatePassword(this.passwordChangeForm).subscribe(
      result => console.log(result),
      error => this.handlePasswordChange(error));
  }

  handlePasswordChange(error) {
    if (error.status === 409) {
      this.message = 'Current password is wrong!';
      this.showPasswordChangeErrorMessage = true;
    }
    if (error.status === 200) {
      this.message = 'Password changed';
      this.showPasswordChangeErrorMessage = false;
      this.openSuccessModal();
    }
  }

  emailSubmit() {
    this.emailChangeForm.userId = this.tokenStorageService.getUser().id;
    this.userService.updateEmail(this.emailChangeForm).subscribe(
      result => console.log(result),
        error => this.handleEmailChange(error));
  }

  handleEmailChange(error) {
    if (error.status === 409) {
      this.message = 'Email is already taken!';
      this.showEmailChangeErrorMessage = true;
    }
    if (error.status === 200) {
      this.message = 'Email changed. Login with new credentials.';
      this.showEmailChangeErrorMessage = false;
      this.tokenStorageService.signOut();
      this.openSuccessModal();
    }
  }

  refreshPage() {
    window.location.replace('/profile');
  }
}
