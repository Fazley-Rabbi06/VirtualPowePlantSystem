package com.powerplantsystem.virtualpowerplantsystem.service;

import com.powerplantsystem.virtualpowerplantsystem.dto.BatteryStatistics;
import com.powerplantsystem.virtualpowerplantsystem.entity.Battery;

import java.util.List;

public interface BatteryService {
    List<Battery> saveBatteries(List<Battery> batteries);
    List<Battery> getBatteriesByPostcodeRange(String postcodeStart, String postcodeEnd);
    BatteryStatistics getBatteryStatistics(List<Battery> batteries);
}
