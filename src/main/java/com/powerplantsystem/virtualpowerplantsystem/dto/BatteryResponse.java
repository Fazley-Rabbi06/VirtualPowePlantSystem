package com.powerplantsystem.virtualpowerplantsystem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
@SuperBuilder
public class BatteryResponse {
    private List<String> batteries;
    private int totalWattCapacity;
    private double averageWattCapacity;

    public BatteryResponse(List<String> batteries, int totalWattCapacity, double averageWattCapacity) {
        this.batteries = batteries;
        this.totalWattCapacity = totalWattCapacity;
        this.averageWattCapacity = averageWattCapacity;
    }

}
