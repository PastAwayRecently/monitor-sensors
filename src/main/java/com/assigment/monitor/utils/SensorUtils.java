package com.assigment.monitor.utils;

import com.assigment.monitor.dto.SensorRequestDTO;
import com.assigment.monitor.model.Sensor;
import org.springframework.stereotype.Component;

@Component
public class SensorUtils {

    public static Sensor populate(Sensor sensor, SensorRequestDTO sensorRequestDTO) {
        sensor.setName(sensorRequestDTO.getName());
        sensor.setModel(sensorRequestDTO.getModel());
        sensor.setType(sensorRequestDTO.getType());
        sensor.setUnit(sensorRequestDTO.getUnit());
        sensor.setLocation(sensorRequestDTO.getLocation());
        sensor.setDescription(sensorRequestDTO.getDescription());
        return sensor;
    }

}
