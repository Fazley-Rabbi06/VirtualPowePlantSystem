package com.powerplantsystem.virtualpowerplantsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class BatteryStatistics {
    private int totalWattCapacity;
    private double averageWattCapacity;

    public BatteryStatistics(int totalWattCapacity, double averageWattCapacity) {
        this.totalWattCapacity = totalWattCapacity;
        this.averageWattCapacity = averageWattCapacity;
    }

}
