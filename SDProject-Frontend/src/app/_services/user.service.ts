import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../_models/user.model';
import { Device } from '../_models/device.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  url: string = environment.usersApiUrl;
  constructor(private http: HttpClient) { }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.url + '/users/');
  }

  getCurrentUser(): User {
    return JSON.parse(localStorage.getItem('currentUser')!);
  }

  getUserById(id: string): Observable<User> {
    return this.http.get<User>(this.url + `/users/${id}`);
  }

  addUser(user: User): Observable<User> {
    return this.http.post<User>(this.url + '/users', user);
  }

  deleteUser(id: string): Observable<unknown> {
    return this.http.delete(this.url + `/users/delete/${id}`);
  }
  
  updateUser(user: User, id:string): Observable<User> {
    return this.http.put<User>(this.url + `/users/update/${id}`, user);
  }

  getDevicesByUserId(id: string): Observable<Device> {
    return this.http.get<Device>(this.url + `/devices/users/${id}/`);
  }
}
