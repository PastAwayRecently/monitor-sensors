package com.assigment.monitor.controller;

import com.assigment.monitor.model.Sensor;
import com.assigment.monitor.service.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Operation(
            summary = "Add new sensor",
            description = "Creates new sensor and saves it in database",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sensor created"),
                    @ApiResponse(responseCode = "400", description = "Invalid data")
            }
    )
    @PostMapping
    public Sensor addSensor(@RequestBody Sensor sensor) {
        return sensorService.saveSensor(sensor);
    }

    @Operation(
            summary = "Get all sensors",
            description = "Returns list of all sensors",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List returns")
            }
    )
    @GetMapping
    public List<Sensor> getAllSensors() {
        return sensorService.getAllSensors();
    }

    @Operation(
            summary = "Get sensor by ID",
            description = "Returns sensor by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sensor is found"),
                    @ApiResponse(responseCode = "404", description = "Sensor is not found")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Sensor> getSensorById(@PathVariable Long id) {
        Optional<Sensor> sensor = sensorService.getSensorById(id);
        return sensor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete sensor by ID",
            description = "Deletes sensor by ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Sensor was deleted"),
                    @ApiResponse(responseCode = "404", description = "Sensor was not deleted")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable Long id) {
        sensorService.deleteSensor(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Update sensor by ID",
            description = "Updates sensor by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sensor is updated"),
                    @ApiResponse(responseCode = "400", description = "Invalid data"),
                    @ApiResponse(responseCode = "404", description = "Sensor is not found")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Sensor> updateSensor(@PathVariable Long id, @RequestBody Sensor updatedSensor) {
        Optional<Sensor> sensor = sensorService.updateSensor(id, updatedSensor);
        return sensor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Search sensor by name or model",
            description = "Searches sensor by name or model",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sensor is found"),
                    @ApiResponse(responseCode = "404", description = "Sensor is not found")
            }
    )
    @GetMapping("/search")
    public List<Sensor> searchSensors(@RequestParam String query) {
        return sensorService.searchByNameOrModel(query);
    }

}
