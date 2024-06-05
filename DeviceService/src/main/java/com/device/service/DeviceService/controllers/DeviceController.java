package com.device.service.DeviceService.controllers;

import com.device.service.DeviceService.entities.Device;
import com.device.service.DeviceService.entities.UserId;
import com.device.service.DeviceService.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/devices")
@CrossOrigin
public class DeviceController{

    @Autowired
    private DeviceService deviceService;
    //create device
    @PostMapping
    public ResponseEntity<Device> create(@RequestBody Device device) {
        Device device1 = deviceService.create(device);
        return ResponseEntity.status(HttpStatus.CREATED).body(device1);
    }
    //get all devices

    @GetMapping
    public ResponseEntity<List<Device>> getDevices(){
        return ResponseEntity.ok(deviceService.getDevices());

    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Device>> getDevicesByUserId(@PathVariable UserId userId){
        return ResponseEntity.ok(deviceService.getDeviceByUserId(userId));
    }

    @DeleteMapping("/delete/{deviceId}")
    public ResponseEntity<Device> deleteDevice(@PathVariable String deviceId) throws IOException{
        deviceService.delete(deviceId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{deviceId}")
    public ResponseEntity<Device> updateDevice(@PathVariable String deviceId, @RequestBody Device updatedDevice) {
        Device updated = deviceService.update(deviceId, updatedDevice);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
