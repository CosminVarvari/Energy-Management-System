package com.user.UserMicroservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Device {
    private Long id;
    private Long userId;
    private String username;
    private String description;
    private String address;
    private Long maximConsumption;
}
