import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { User } from 'src/app/_models/user.model';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-dialog-add-user',
  templateUrl: './dialog-add-user.component.html',
  styleUrls: ['./dialog-add-user.component.scss']
})
export class DialogAddUserComponent {
  userForm = new FormGroup({
    name: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl(''),
    username: new FormControl(''),
    about: new FormControl(''),
  })

  constructor(private userService: UserService) {}

  onSubmit() {
    let newUser = new User();
    newUser.name = this.userForm.value.name!;
    newUser.email = this.userForm.value.email!;
    newUser.password = this.userForm.value.password!;
    newUser.username = this.userForm.value.username!;
    newUser.about = this.userForm.value.about!;
    this.userService.addUser(newUser).subscribe();
  }
}
