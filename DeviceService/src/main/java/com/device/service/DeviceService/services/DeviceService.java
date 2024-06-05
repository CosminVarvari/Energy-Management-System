package com.device.service.DeviceService.services;

import com.device.service.DeviceService.entities.Device;
import com.device.service.DeviceService.entities.UserId;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public interface DeviceService {
    //create
    Device create(Device device);
    //get all devices
    List<Device> getDevices();
    //get all by UserId
    List<Device> getDeviceByUserId(UserId userId);

    void delete(String deviceId) throws IOException;

    Device update(String deviceId, Device updatedDevice);
}
