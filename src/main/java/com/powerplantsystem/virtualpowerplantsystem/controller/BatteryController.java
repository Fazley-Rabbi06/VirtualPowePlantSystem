package com.powerplantsystem.virtualpowerplantsystem.controller;

import com.powerplantsystem.virtualpowerplantsystem.dto.BatteryResponse;
import com.powerplantsystem.virtualpowerplantsystem.dto.BatteryStatistics;
import com.powerplantsystem.virtualpowerplantsystem.entity.Battery;
import com.powerplantsystem.virtualpowerplantsystem.service.BatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import com.powerplantsystem.virtualpowerplantsystem.validator.BatteryValidator;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/batteries")
public class BatteryController {
    @Autowired
    private BatteryService batteryService;

    @Autowired
    private BatteryValidator batteryValidator;

    @PostMapping
    public ResponseEntity<?> addBatteries(@RequestBody List<Battery> batteries, BindingResult result) {
        // Validate the list of batteries
        batteryValidator.validate(batteries, result);

        if (result.hasErrors()) {
            // Collect all validation errors
            List<String> errorMessages = result.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            // Return bad request with error messages
            return ResponseEntity.badRequest().body(errorMessages);
        }

        // Save the batteries and return the saved entities
        return ResponseEntity.ok(batteryService.saveBatteries(batteries));
    }


    @GetMapping
    public BatteryResponse getBatteriesInRange(
            @RequestParam String postcodeStart,
            @RequestParam String postcodeEnd) {
        List<Battery> batteries = batteryService.getBatteriesByPostcodeRange(postcodeStart, postcodeEnd);
        batteries.sort(Comparator.comparing(Battery::getName));
        BatteryStatistics stats = batteryService.getBatteryStatistics(batteries);

        List<String> batteryNames = batteries.stream().map(Battery::getName).collect(Collectors.toList());

        return new BatteryResponse(batteryNames, stats.getTotalWattCapacity(), stats.getAverageWattCapacity());
    }
}
