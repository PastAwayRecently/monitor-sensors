package com.assigment.monitor.controller;

import com.assigment.monitor.dto.SensorRequestDTO;
import com.assigment.monitor.dto.SensorResponseDTO;
import com.assigment.monitor.model.Sensor;
import com.assigment.monitor.service.SensorService;
import com.assigment.monitor.utils.SensorUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Shmarlouski
 */
@RestController
@RequestMapping("/sensors")
@Tag(name = "Sensor API", description = "API for sensor monitoring")
public class SensorController {

    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Operation(summary = "Add new sensor", description = "Creates new sensor and saves it in database")
    @PostMapping
    public ResponseEntity<SensorResponseDTO> addSensor(@Valid @RequestBody SensorRequestDTO sensorRequestDTO) {
        Sensor sensor = new Sensor();
        SensorUtils.populate(sensor, sensorRequestDTO);

        Sensor savedSensor = sensorService.saveSensor(sensor);
        return ResponseEntity.ok(SensorResponseDTO.fromEntity(savedSensor));
    }

    @Operation(summary = "Get all sensors", description = "Returns list of all sensors")
    @GetMapping
    public ResponseEntity<List<SensorResponseDTO>> getAllSensors() {
        List<Sensor> sensors = sensorService.getAllSensors();
        List<SensorResponseDTO> responseDTOs = sensors.stream()
                .map(SensorResponseDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(responseDTOs);
    }

    @Operation(summary = "Get sensor by ID", description = "Returns sensor by ID")
    @GetMapping("/{id}")
    public ResponseEntity<SensorResponseDTO> getSensorById(@PathVariable Long id) {
        Optional<Sensor> sensor = sensorService.getSensorById(id);
        return sensor.map(s -> ResponseEntity.ok(SensorResponseDTO.fromEntity(s)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete sensor by ID", description = "Deletes sensor by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable Long id) {
        sensorService.deleteSensor(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update sensor by ID", description = "Updates sensor by ID")
    @PutMapping("/{id}")
    public ResponseEntity<SensorResponseDTO> updateSensor(
            @PathVariable Long id,
            @Valid @RequestBody SensorRequestDTO sensorRequestDTO
    ) {
        Optional<Sensor> sensorOpt = sensorService.getSensorById(id);
        if (sensorOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Sensor sensor = sensorOpt.get();
        SensorUtils.populate(sensor, sensorRequestDTO);

        Sensor updatedSensor = sensorService.saveSensor(sensor);
        return ResponseEntity.ok(SensorResponseDTO.fromEntity(updatedSensor));
    }

    @Operation(summary = "Search sensor by name or model", description = "Searches sensor by name or model")
    @GetMapping("/search")
    public ResponseEntity<List<SensorResponseDTO>> searchSensors(@RequestParam String query) {
        List<Sensor> sensors = sensorService.searchByNameOrModel(query);
        List<SensorResponseDTO> responseDTOs = sensors.stream()
                .map(SensorResponseDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(responseDTOs);
    }
}