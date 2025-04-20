package com.assigment.monitor.dto;

import com.assigment.monitor.model.Sensor;
import com.assigment.monitor.model.SensorType;
import com.assigment.monitor.model.SensorUnit;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SensorRequestDTO {

    private Integer id;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
    private String name;

    @NotBlank(message = "Model is required")
    @Size(max = 15, message = "Model must not exceed 15 characters")
    private String model;

    @NotNull(message = "SensorType is required")
    private SensorType type;

    @NotNull
    private SensorUnit unit;

    @NotNull
    @Min(value = 0, message = "Value must be positive or zero")
    private Integer rangeFrom;

    @NotNull
    @Min(value = 0, message = "Value must be positive or zero")
    private Integer rangeTo;

    @Size(max = 40, message = "Location must not exceed 40 characters")
    private String location;

    @Size(max = 200, message = "Description must not exceed 200 characters")
    private String description;

    public static SensorRequestDTO fromEntity(Sensor sensor) {
        SensorRequestDTO dto = new SensorRequestDTO();
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