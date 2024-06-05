package com.mc.service.mc.Entities;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.core.serializer.Serializer;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "micro_devices")
public class mcDevice implements Serializable {
    @Id
    @Column(name= "id")
    private String deviceId;
    @ManyToOne()
    @JoinColumn(name = "userId")
    private mcUserId userID;
    @Column(name="DESCRIPTION")
    private String description;
    @Column(name="ADDRESS")
    private String address;
    @Column(name="MAXHOUREC")
    private int maxhourec;
}
