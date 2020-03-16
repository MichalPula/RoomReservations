import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './services/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'frontend';

  private roles: string[];
  isLoggedIn = false;
  loggedUserIsAdmin = false;
  username: string;

  constructor(private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getUser();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.loggedUserIsAdmin = this.roles.includes('ROLE_ADMIN');
      this.username = user.username;
    }
  }

  logout() {
    this.isLoggedIn = false;
    this.tokenStorageService.signOut();
  }
}
