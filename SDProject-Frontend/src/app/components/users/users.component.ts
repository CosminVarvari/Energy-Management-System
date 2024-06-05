import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Subscription, concatMap, from, map, switchMap } from 'rxjs';
import { User } from 'src/app/_models/user.model';
import { UserService } from 'src/app/_services/user.service';
import { DialogAddUserComponent } from '../dialog-add-user/dialog-add-user.component';
import { DialogDeleteUserComponent } from '../dialog-delete-user/dialog-delete-user.component';
import { DialogEditUserComponent } from '../dialog-edit-user/dialog-edit-user.component';
import { Device } from 'src/app/_models/device.model';
import { DeviceService } from 'src/app/_services/devices.service';
import { DialogAddDeviceComponent } from '../dialog-add-device/dialog-add-device.component';
import { DialogDeleteDeviceComponent } from '../dialog-delete-device/dialog-delete-device.component';
import { DialogEditDeviceComponent } from '../dialog-edit-device/dialog-edit-device.component';

export class UserDevice {
  user!: User;
  devices!: Device[];
}

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit, OnDestroy {
  userDeviceDict: UserDevice[] = [];
  currentDevices: Device[] = [];
  users: User[] = [];
  subscription: Subscription = new Subscription();
  
  constructor(private userService: UserService, private deviceService: DeviceService, private router: Router, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.getAllUsers();
  }

  getAllUsers(): void {
    this.userService.getAllUsers().pipe(
      switchMap(data =>
        from(data).pipe(
          concatMap(el => this.deviceService.getDevicesByUserId(el.userId).pipe(
            map(deviceResult => ({ user: el, device: deviceResult }))
            )
          )
        )
      )
    ).subscribe(result => {
      let userDevice = new UserDevice();
      userDevice.user = result.user;
      userDevice.devices = result.device;
      if(result.user.role !== "admin") {
        this.userDeviceDict.push(userDevice);
      }
    });
  }



  openDialogAdd() {
    const dialogRef = this.dialog.open(DialogAddUserComponent);
    dialogRef.afterClosed().subscribe(result => {
      if (result == true) {
        window.location.reload();
      }
    });
  }

  openDialogAddDevice(userid: string) {
    const dialogRef = this.dialog.open(DialogAddDeviceComponent, {
      data: {
        userId: userid
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result == true) {
        window.location.reload();
      }
    });
  }

  openDialogDelete(deviceId: string) {
    console.log(deviceId);
    const dialogRef = this.dialog.open(DialogDeleteDeviceComponent, {
      data: {
        deviceId: deviceId
      },
    });
    dialogRef.afterClosed().subscribe(result => {
      if(result == true) {
        window.location.reload();
      }
    });
  }

  openDialogDeleteUser(userId: string) {
    const dialogRef = this.dialog.open(DialogDeleteUserComponent, {
      data: {
        userId: userId
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result == true) {
        window.location.reload();
      }
    });
  }

  openDialogEditDevice(device: Device) {
    const dialogRef = this.dialog.open(DialogEditDeviceComponent, {
      data: device,
      id: device.deviceId
    });
    dialogRef.afterClosed().subscribe(result => {
      if(result == true) {
        window.location.reload();
      }
    });
  }

  openDialogEditUser(user: User) {
    const dialogRef = this.dialog.open(DialogEditUserComponent, {
      data: user,
      id: user.userId
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result == true) {
        window.location.reload();
      }
    });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
