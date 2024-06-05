package com.device.service.DeviceService.controllers;

import com.device.service.DeviceService.entities.Device;
import com.device.service.DeviceService.entities.UserId;
import com.device.service.DeviceService.services.UserIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/devices/users")
@CrossOrigin
public class UserIdController {


    @Autowired
    private UserIdService userIdService;

    //create UserId
    @PostMapping("/{userid}")
    public ResponseEntity create(@PathVariable String userid){
        UserId userid1 = userIdService.create(userid);
        return ResponseEntity.status(HttpStatus.CREATED).body(userid1);
    }

    @GetMapping
    public ResponseEntity<List<UserId>> getUserIds(){
        return ResponseEntity.ok(userIdService.getAllUserIds());

    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Device> deleteUserId(@PathVariable String userId) throws IOException {
        userIdService.delete(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
