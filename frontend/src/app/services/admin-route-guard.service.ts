import { Injectable } from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {TokenStorageService} from './token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class AdminRouteGuardService implements CanActivate {

  constructor(private tokenService: TokenStorageService, private router: Router) { }

  canActivate(): boolean {
    if (this.tokenService.isAdmin()) {
      return true;
    } else {
      this.router.navigate(['/home']);
      return false;
    }
  }
}
