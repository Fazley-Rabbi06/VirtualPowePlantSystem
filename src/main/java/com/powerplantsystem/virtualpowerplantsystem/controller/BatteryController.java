package com.powerplantsystem.virtualpowerplantsystem.controller;

import com.powerplantsystem.virtualpowerplantsystem.dto.BatteryResponse;
import com.powerplantsystem.virtualpowerplantsystem.dto.BatteryStatistics;
import com.powerplantsystem.virtualpowerplantsystem.entity.Battery;
import com.powerplantsystem.virtualpowerplantsystem.service.BatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/batteries")
public class BatteryController {
    @Autowired
    private BatteryService batteryService;

    @PostMapping
    public List<Battery> addBatteries(@RequestBody List<Battery> batteries) {
        return batteryService.saveBatteries(batteries);
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
