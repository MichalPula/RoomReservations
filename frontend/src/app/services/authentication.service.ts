import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

const API_AUTH = 'http://localhost:8080/';

@Injectable({
  providedIn: 'root'
})

export class AuthenticationService {

  constructor(private http: HttpClient) { }

  login(loginForm): Observable<any> {
    return this.http.post(API_AUTH + 'login', loginForm);
  }

  register(registerForm): Observable<any> {
    return this.http.post(API_AUTH + 'register', registerForm);
  }
}
