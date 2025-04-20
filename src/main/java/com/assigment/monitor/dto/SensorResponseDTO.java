package com.assigment.monitor.dto;

import com.assigment.monitor.model.Sensor;
import com.assigment.monitor.model.SensorType;
import com.assigment.monitor.model.SensorUnit;
import lombok.Data;

@Data
public class SensorResponseDTO {
    private Integer id;
    private String name;
    private String model;
    private SensorType type;
    private SensorUnit unit;
    private Integer rangeFrom;
    private Integer rangeTo;
    private String location;
    private String description;

    public static SensorResponseDTO fromEntity(Sensor sensor) {
        SensorResponseDTO dto = new SensorResponseDTO();
        dto.setId(sensor.getId());
        dto.setName(sensor.getName());
        dto.setModel(sensor.getModel());
        dto.setType(sensor.getType());
        dto.setUnit(sensor.getUnit());
        dto.setRangeFrom(sensor.getRangeFrom());
        dto.setRangeTo(sensor.getRangeTo());
        dto.setLocation(sensor.getLocation());
        dto.setDescription(sensor.getDescription());
        return dto;
    }
}