import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Device } from '../_models/device.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DeviceService {
  url: string = environment.deviceApiUrl;
  constructor(private http: HttpClient) { }

  getAllDevices(): Observable<Device[]> {
    return this.http.get<Device[]>(this.url + '/devices');
  }

  getDevicesByUserId(id: string): Observable<Device[]> {
    return this.http.get<Device[]>(this.url + `/devices/users/${id}`);
  }

  addDevice(device: Device): Observable<Device> {
    return this.http.post<Device>(this.url + '/devices', device);
  }

  deleteDevice(id: string): Observable<unknown> {
    return this.http.delete(this.url + `/devices/delete/${id}`);
  }
  
  updateDevice(device: Device, id: string): Observable<Device> {
    return this.http.put<Device>(this.url + `/devices/update/${id}`, device);
  }
}
