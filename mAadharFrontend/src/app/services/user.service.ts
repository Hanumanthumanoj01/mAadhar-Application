import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

const applyForAadhar = 'http://localhost:8093/AadharApp/citizens/issueAadhar';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  //headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private httpClient: HttpClient, private router: Router) {}

  applyForAadhar(data: {
    citizenId: String;
    passportId: String;
  }): Observable<any> {
    return this.httpClient.post(applyForAadhar, data, {
      withCredentials: true,
    });
  }
}
