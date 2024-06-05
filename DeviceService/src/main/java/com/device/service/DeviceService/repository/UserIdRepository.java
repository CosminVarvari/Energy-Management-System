package com.device.service.DeviceService.repository;

import com.device.service.DeviceService.entities.Device;
import com.device.service.DeviceService.entities.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserIdRepository extends JpaRepository<UserId, String>{
    UserId findByUserId(String userid);
}
