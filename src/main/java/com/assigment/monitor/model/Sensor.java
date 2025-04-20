package com.assigment.monitor.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shmarlouski
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "sensors")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
    private String name;

    @NotBlank(message = "Model is required")
    @Size(max = 15, message = "Model must not exceed 15 characters")
    private String model;

    private Integer rangeFrom;

    private Integer rangeTo;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "SensorType is required")
    private SensorType type;

    @Enumerated(EnumType.STRING)
    private SensorUnit unit;

    @Size(max = 40, message = "Location must not exceed 40 characters")
    private String location;

    @Size(max = 200, message = "Description must not exceed 200 characters")
    private String description;

}