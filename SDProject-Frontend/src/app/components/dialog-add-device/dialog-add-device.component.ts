import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Device } from 'src/app/_models/device.model';
import { UserId } from 'src/app/_models/userid.model';
import { DeviceService } from 'src/app/_services/devices.service';

@Component({
  selector: 'app-dialog-add-device',
  templateUrl: './dialog-add-device.component.html',
  styleUrls: ['./dialog-add-device.component.scss']
})

export class DialogAddDeviceComponent {
  deviceForm = new FormGroup({
    description: new FormControl(''),
    address: new FormControl(''),
    maxhourec: new FormControl(''),
  });
  
  constructor(private deviceService: DeviceService,  @Inject(MAT_DIALOG_DATA) public data: any) {}


  onSubmit() {
    let newDevice = new Device();
    newDevice.address = this.deviceForm.value.address!;
    newDevice.maxhourec = this.deviceForm.value.maxhourec!;
    newDevice.description = this.deviceForm.value.description!;
    let userId = new UserId();
    userId.userId = this.data.userId;
    newDevice.userID = userId;
    console.log(newDevice.userID);
    this.deviceService.addDevice(newDevice).subscribe();
  }
}
