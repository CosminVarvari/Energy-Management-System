import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { LoginService } from './login.service';
import { User } from '../_models/user.model';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class AdminGuardService implements CanActivate{
  constructor(public userService: UserService, public router: Router) {}
  
  canActivate(): boolean {
    console.log(this.userService.getCurrentUser());
    if (this.userService.getCurrentUser().role !== 'admin') {
      this.router.navigate(['home']);
      return false;
    }
    return true;
  }
}
