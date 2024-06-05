package com.device.service.DeviceService.services;

import com.device.service.DeviceService.entities.UserId;

import java.io.IOException;
import java.util.List;

public interface UserIdService {

    UserId create(String userid);
    //get all UserId

    List<UserId> getAllUserIds();

    void delete(String userid) throws IOException;


}
