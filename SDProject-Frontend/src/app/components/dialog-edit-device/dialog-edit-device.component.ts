import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormControl, FormGroup } from '@angular/forms';
import { Device } from 'src/app/_models/device.model';
import { DeviceService } from 'src/app/_services/devices.service';

@Component({
  selector: 'app-dialog-edit-device',
  templateUrl: './dialog-edit-device.component.html',
  styleUrls: ['./dialog-edit-device.component.scss']
})
export class DialogEditDeviceComponent implements OnInit {
  deviceForm = new FormGroup({
    description: new FormControl(''),
    address: new FormControl(''),
    maxhourec: new FormControl('')
  });
  device!: Device;
  
  constructor(@Inject(MAT_DIALOG_DATA) public data: Device, private deviceService: DeviceService) {}

  ngOnInit(): void {
    this.device = this.data;
    this.deviceForm.setValue({
      address: this.device.address, 
      maxhourec: this.device.maxhourec,
      description: this.device.description
    });
  }

  onSubmit() {
    let newDevice = new Device();
    newDevice.deviceId = this.device.deviceId;
    newDevice.address = this.deviceForm.value.address!;
    newDevice.maxhourec = this.deviceForm.value.maxhourec!;
    newDevice.description = this.deviceForm.value.description!;
    this.deviceService.updateDevice(newDevice, this.device.deviceId).subscribe();
  }

}

