import { Component, OnInit } from '@angular/core';
import {UserService} from '../services/user.service';
import {TokenStorageService} from '../services/token-storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  content: '';
  roles: string[] = [];

  constructor(private userService: UserService, private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    if (this.tokenStorageService.getToken()) {
      this.roles = this.tokenStorageService.getUser().roles;
    }
    // this.userService.getPublicContent().subscribe(
    //   data => {
    //     console.log(data + ' LOG OF DATA');
    //     this.content = JSON.parse(data);
    //   }, error => {
    //      this.content = error.error.message;
    //   }
    // );
  }

}
