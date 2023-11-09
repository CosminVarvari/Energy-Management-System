import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { User } from 'src/app/_models/user.model';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-dialog-edit-user',
  templateUrl: './dialog-edit-user.component.html',
  styleUrls: ['./dialog-edit-user.component.scss']
})
export class DialogEditUserComponent implements OnInit{
  userForm = new FormGroup({
    name: new FormControl(''),
    email: new FormControl(''),
    username: new FormControl(''),
    about: new FormControl('')
  });
  user!: User;

  constructor(@Inject(MAT_DIALOG_DATA) public data: User, private userService: UserService) {}

  ngOnInit(): void {
    this.user = this.data;
    this.userForm.setValue({
      name: this.user.name, 
      about: this.user.about,
      email: this.user.email,
      username: this.user.username,
    });
  }

  onSubmit() {
    let newUser = new User();
    newUser.name = this.userForm.value.name!;
    newUser.email = this.userForm.value.email!;
    newUser.username = this.userForm.value.username!;
    newUser.about = this.userForm.value.about!;
    this.userService.updateUser(newUser, this.user.userId).subscribe();
  }

}
