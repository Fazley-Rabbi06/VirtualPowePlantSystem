package com.powerplantsystem.virtualpowerplantsystem.service.implementation;

import com.powerplantsystem.virtualpowerplantsystem.dto.BatteryStatistics;
import com.powerplantsystem.virtualpowerplantsystem.entity.Battery;
import com.powerplantsystem.virtualpowerplantsystem.repository.BatteryRepository;
import com.powerplantsystem.virtualpowerplantsystem.service.BatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatteryServiceImpl implements BatteryService {
    @Autowired
    private BatteryRepository batteryRepository;

    @Override
    public List<Battery> saveBatteries(List<Battery> batteries) {
        return batteryRepository.saveAll(batteries);
    }

    @Override
    public List<Battery> getBatteriesByPostcodeRange(String postcodeStart, String postcodeEnd) {
        return batteryRepository.findByPostcodeBetween(postcodeStart, postcodeEnd);
    }

    @Override
    public BatteryStatistics getBatteryStatistics(List<Battery> batteries) {
        int totalWattCapacity = batteries.stream().mapToInt(Battery::getWattCapacity).sum();
        double averageWattCapacity = batteries.stream().mapToInt(Battery::getWattCapacity).average().orElse(0.0);
        return new BatteryStatistics(totalWattCapacity, averageWattCapacity);
    }
}
