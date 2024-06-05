package com.device.service.DeviceService.repository;

import com.device.service.DeviceService.entities.Device;
import com.device.service.DeviceService.entities.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DeviceRepository extends JpaRepository<Device, Long> {
    //custom finder method
    List<Device> findByUserID(UserId userId);

    Device findByDeviceId(String deviceId);

}
