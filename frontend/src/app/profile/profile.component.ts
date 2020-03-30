import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {TokenStorageService} from '../services/token-storage.service';
import {UserService} from '../services/user.service';
import {catchError} from 'rxjs/operators';
import {ok} from 'assert';

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

  @ViewChild('passwordChangeSuccessModal') passwordChangeSuccessModalContent: TemplateRef<any>;
  @ViewChild('emailChangeSuccessModal') emailChangeSuccessModalContent: TemplateRef<any>;

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
  message: string;

  constructor(private modalService: NgbModal, private tokenStorageService: TokenStorageService,
              private userService: UserService) { }

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

  openPasswordChangeSuccessModal() {
    this.modalService.open(this.passwordChangeSuccessModalContent, { centered: true });
  }

  openEmailChangeSuccessModal() {
    this.modalService.open(this.passwordChangeSuccessModalContent, { centered: true });
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
      this.openPasswordChangeSuccessModal();
    }
  }

  emailSubmit() {
    this.emailChangeForm.userId = this.tokenStorageService.getUser().id;
    this.userService.updateEmail(this.emailChangeForm).subscribe(data => {
      this.handleEmailChange();
    });
  }

  handleEmailChange() {
    this.message = 'Login with new credentials';
    this.openPasswordChangeSuccessModal();
    this.tokenStorageService.signOut();
  }

  refreshPage() {
    window.location.replace('/profile');
  }
}
