package com.device.service.DeviceService.services.impl;

import com.device.service.DeviceService.entities.Device;
import com.device.service.DeviceService.entities.UserId;
import com.device.service.DeviceService.repository.DeviceRepository;
import com.device.service.DeviceService.services.DeviceService;
import com.user.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Service
public class DeviceServiceImpl implements DeviceService {


    @Autowired
    private DeviceRepository repository;
    @Override
    public Device create(Device device) {
        String randomDeviceId = UUID.randomUUID().toString();
        device.setDeviceId(randomDeviceId);
        return repository.save(device);
    }

    @Override
    public List<Device> getDevices() {
        return repository.findAll();
    }

    @Override
    public List<Device> getDeviceByUserId(UserId userId) {
        return repository.findByUserID(userId);
    }

    @Override
    public void delete(String deviceId) throws IOException {
        Device device = repository.findByDeviceId(deviceId);
        if (device != null) {
            repository.delete(device);
        } else {
            throw new ResourceNotFoundException(Device.class.getSimpleName() + " with id: " + deviceId);
        }
    }


    @Override
    public Device update(String deviceId, Device updatedDevice) {
        Device existingDevice = repository.findByDeviceId(deviceId);
        if (existingDevice != null) {
            // Update the properties of the existing device with the values from the updatedDevice
            existingDevice.setDescription(updatedDevice.getDescription());
            existingDevice.setAddress(updatedDevice.getAddress());
            existingDevice.setMaxhourec(updatedDevice.getMaxhourec());
            // Save the updated device
            return repository.save(existingDevice);
        } else {
            throw new ResourceNotFoundException(Device.class.getSimpleName() + " with id: " + deviceId);
        }
    }
}
