package com.assigment.monitor.service;

import com.assigment.monitor.model.Sensor;
import com.assigment.monitor.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Shmarlouski
 */
@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Sensor saveSensor(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    public Optional<Sensor> getSensorById(Long id) {
        return sensorRepository.findById(id);
    }

    public void deleteSensor(Long id) {
        sensorRepository.deleteById(id);
    }

    public Optional<Sensor> updateSensor(Long id, Sensor updatedSensor) {
        return sensorRepository.findById(id)
                .map(existingSensor -> {
                    existingSensor.setName(updatedSensor.getName());
                    existingSensor.setModel(updatedSensor.getModel());
                    existingSensor.setRange(updatedSensor.getRange());
                    existingSensor.setType(updatedSensor.getType());
                    existingSensor.setUnit(updatedSensor.getUnit());
                    existingSensor.setLocation(updatedSensor.getLocation());
                    existingSensor.setDescription(updatedSensor.getDescription());
                    return sensorRepository.save(existingSensor);
                });
    }

    public List<Sensor> searchByNameOrModel(String query) {
        return sensorRepository.searchByNameOrModel(query);
    }


}