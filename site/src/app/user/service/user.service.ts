import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {AuthService} from "../../auth/service/auth.service";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = "http://localhost:8080";

  constructor(private http: HttpClient,
              private authService: AuthService) {}

  getUserById(userId: number): Observable<any> {
    const token = this.authService.getToken();
    if (!token) {
      return throwError('User not authenticated');
    }
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.http.get<any>(`${this.baseUrl}/user/${userId}`, { headers }).pipe(
      catchError(error => {
        console.error('Error getting user by ID:', error);
        return throwError(error);
      })
    );
  }

  updateUser(user: any): Observable<any> {
    const token = localStorage.getItem('auth_token');
    if (!token) {
      return throwError('User not authenticated');
    }
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
    return this.http.put<any>(`${this.baseUrl}/user/${user.id}`, user, { headers }).pipe(
      catchError(error => {
        console.error('Error updating user:', error);
        return throwError(error);
      })
    );
  }
}
