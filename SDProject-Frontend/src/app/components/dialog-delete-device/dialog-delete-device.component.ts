import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DeviceService } from 'src/app/_services/devices.service';

@Component({
  selector: 'app-dialog-delete-device',
  templateUrl: './dialog-delete-device.component.html',
  styleUrls: ['./dialog-delete-device.component.scss']
})
export class DialogDeleteDeviceComponent implements OnInit{
  id!: string;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private deviceService: DeviceService) {}

  ngOnInit() {
    this.id = this.data.deviceId;
  }

  onSubmit() {
    console.log(this.data);
    this.deviceService.deleteDevice(this.id).subscribe();
  }

}