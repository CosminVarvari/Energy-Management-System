package com.mc.service.mc.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "mc_sensor")
public class mcSensor implements Serializable {
    @Id
    @Column(name="id")
    private String sensorId;
    @Column(name= "deviceId")
    private String deviceId;
    @ManyToOne()
    @JoinColumn(name = "userId")
    private mcUserId userID;
    @Column(name="timestamp")
    private int timestamp;
    @Column(name="measurement")
    private int measurement;
}

