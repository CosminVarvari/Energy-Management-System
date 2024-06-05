import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Device } from 'src/app/_models/device.model';
import { DeviceService } from 'src/app/_services/devices.service';
import { DialogEditDeviceComponent } from '../dialog-edit-device/dialog-edit-device.component';
import { DialogDeleteDeviceComponent } from '../dialog-delete-device/dialog-delete-device.component';
import { DialogAddDeviceComponent } from '../dialog-add-device/dialog-add-device.component';
import { User } from 'src/app/_models/user.model';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-devices',
  templateUrl: './devices.component.html',
  styleUrls: ['./devices.component.scss']
})
export class DevicesComponent implements OnInit, OnDestroy {
  currentDevices: Device[] = [];
  subscription: Subscription = new Subscription();
  device!: Device;
  searchValue: string = "";
  isAvailable: boolean = false;
  panelOpenState = false;
  currentUser!: User;
  constructor(private deviceService: DeviceService, private router: Router, public dialog: MatDialog, private userService: UserService)  { }
  
  ngOnInit(): void { 
    this.getAllDevices();
  }
  
  getAllDevices() {
    this.getCurrentUser();
    console.log(this.currentUser);
    console.log(this.currentUser.userId);
    this.subscription.add(
      this.deviceService.getDevicesByUserId(this.currentUser.userId).subscribe(data => {
        this.currentDevices = data;
      })
    )
  }
  getCurrentUser() {
    this.currentUser = this.userService.getCurrentUser();
  }

  openDialogEdit(device: Device) {
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

  // openDialogDelete(device: Device) {
  //   const dialogRef = this.dialog.open(DialogDeleteDeviceComponent, {
  //     data: device.id,
  //   });
  //   dialogRef.afterClosed().subscribe(result => {
  //     if(result == true) {
  //       window.location.reload();
  //     }
  //   });
  // }

  openDialogAdd() {
    const dialogRef = this.dialog.open(DialogAddDeviceComponent);
    dialogRef.afterClosed().subscribe(result => {
      if(result == true) {
        window.location.reload();
      }
    });
  }


  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
