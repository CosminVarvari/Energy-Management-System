package com.device.service.DeviceService.services.impl;

import com.device.service.DeviceService.entities.Device;
import com.device.service.DeviceService.entities.UserId;
import com.device.service.DeviceService.repository.DeviceRepository;
import com.device.service.DeviceService.repository.UserIdRepository;
import com.device.service.DeviceService.services.DeviceService;
import com.device.service.DeviceService.services.UserIdService;
import com.user.service.exceptions.ResourceNotFoundException;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserIdServiceImpl implements UserIdService {


    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private UserIdRepository repository;
    @Override
    public UserId create(String userid) {
        return repository.save(new UserId(userid));
    }

    @Override
    public List<UserId> getAllUserIds() {
        return repository.findAll();
    }

    @Override
    public void delete(String userid) throws IOException {
        UserId userId = repository.findByUserId(userid);
        if (userId != null) {
            List<Device> devices =  deviceRepository.findByUserID(userId);
            deviceRepository.deleteAll(devices);
            repository.delete(userId);
        } else {
            throw new ResourceNotFoundException(Device.class.getSimpleName() + " with id: " + userId);
        }
    }
}
