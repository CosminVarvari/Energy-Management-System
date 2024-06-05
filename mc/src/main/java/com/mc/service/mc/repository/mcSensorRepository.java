package com.mc.service.mc.Repository;

import com.mc.service.mc.Entities.mcSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface mcSensorRepository extends JpaRepository<mcSensor, Long> {
    // You can add custom query methods here if needed
}