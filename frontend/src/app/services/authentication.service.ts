import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

const API_AUTH = 'http://localhost:8080/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class AuthenticationService {

  constructor(private http: HttpClient) { }

  login(credentials): Observable<any> {
    return this.http.post(API_AUTH + 'login', {
      username: credentials.username,
      password: credentials.password
    }, httpOptions);
  }

  register(user): Observable<any> {
    return this.http.post(API_AUTH + 'register', {
      firstName: user.firstName,
      lastName: user.lastName,
      username: user.username,
      password: user.password
    }, httpOptions);
  }
}
