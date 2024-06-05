import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';
import { User } from 'src/app/_models/user.model';
import { LoginService } from 'src/app/_services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, OnDestroy {
  user: User | undefined;
  subscription: Subscription = new Subscription();

  username: string = "";
  password: string = "";
  show: boolean = false;

  constructor(private loginService: LoginService, private router: Router) { }

  ngOnInit(): void { }

  login(): void {
    this.subscription.add(
      this.loginService.login(this.username, this.password).subscribe(data => {
        this.user = data;
        localStorage.setItem('currentUser', JSON.stringify(this.user));
        if(this.user.role === "admin") {
          this.router.navigate(['admin']);
        }
        else {
          this.router.navigate(['home']);
        }
      })
    );
  }

  toggleShowPassword() {
    this.show = !this.show;
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
