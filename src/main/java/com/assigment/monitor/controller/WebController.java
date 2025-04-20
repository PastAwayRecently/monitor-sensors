package com.assigment.monitor.controller;

import com.assigment.monitor.dto.SensorRequestDTO;
import com.assigment.monitor.model.Sensor;
import com.assigment.monitor.service.SensorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/view/sensors")
public class WebController {

    private final SensorService sensorService;

    public WebController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping
    public String sensorsPage(Model model) {
        model.addAttribute("sensors", sensorService.getAllSensors());
        return "sensors";
    }

    @GetMapping("/add")
    public String addSensorPage(Model model) {
        model.addAttribute("sensorRequest", new SensorRequestDTO());
        return "edit-sensor";
    }

    @GetMapping("/edit/{id}")
    public String editSensorPage(@PathVariable Long id, Model model) {
        Sensor sensor = sensorService.getSensorById(id).orElseThrow();
        model.addAttribute("sensorRequest", SensorRequestDTO.fromEntity(sensor));
        return "edit-sensor";
    }

    @PostMapping("/save")
    public String saveSensor(@ModelAttribute SensorRequestDTO sensorRequest) {
        Sensor sensor = new Sensor();
        if (sensorRequest.getId() != null) {
            sensor.setId(sensorRequest.getId());
        }
        sensor.setName(sensorRequest.getName());
        sensor.setModel(sensorRequest.getModel());
        sensor.setType(sensorRequest.getType());
        sensor.setRangeFrom(sensorRequest.getRangeFrom());
        sensor.setRangeTo(sensorRequest.getRangeTo());
        sensor.setLocation(sensorRequest.getLocation());
        sensor.setDescription(sensorRequest.getDescription());
        sensorService.saveSensor(sensor);
        return "redirect:/view/sensors";
    }

    @GetMapping("/delete/{id}")
    public String deleteSensor(@PathVariable Long id) {
        sensorService.deleteSensor(id);
        return "redirect:/view/sensors";
    }
}