package com.powerplantsystem.virtualpowerplantsystem;

import com.powerplantsystem.virtualpowerplantsystem.dto.BatteryStatistics;
import com.powerplantsystem.virtualpowerplantsystem.entity.Battery;
import com.powerplantsystem.virtualpowerplantsystem.repository.BatteryRepository;
import com.powerplantsystem.virtualpowerplantsystem.service.implementation.BatteryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class BatteryServiceImplTest {

    @Mock
    private BatteryRepository batteryRepository;

    @InjectMocks
    private BatteryServiceImpl batteryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveBatteries() {
        // Arrange
        List<Battery> batteries = Arrays.asList(
                new Battery(1L, "Battery1", "2000", 500),
                new Battery(2L, "Battery2", "2001", 600)
        );
        when(batteryRepository.saveAll(batteries)).thenReturn(batteries);

        // Act
        List<Battery> savedBatteries = batteryService.saveBatteries(batteries);

        // Assert
        assertEquals(batteries.size(), savedBatteries.size());
        assertEquals(batteries.get(0).getName(), savedBatteries.get(0).getName());
    }

    @Test
    void testGetBatteriesByPostcodeRange() {
        // Arrange
        List<Battery> batteries = Arrays.asList(
                new Battery(1L, "Battery1", "2000", 500),
                new Battery(2L, "Battery2", "2001", 600)
        );
        when(batteryRepository.findByPostcodeBetween("2000", "2001")).thenReturn(batteries);

        // Act
        List<Battery> foundBatteries = batteryService.getBatteriesByPostcodeRange("2000", "2001");

        // Assert
        assertEquals(2, foundBatteries.size());
        assertEquals("Battery1", foundBatteries.get(0).getName());
    }

    @Test
    void testGetBatteryStatistics() {
        // Arrange
        List<Battery> batteries = Arrays.asList(
                new Battery(1L, "Battery1", "2000", 500),
                new Battery(2L, "Battery2", "2001", 600)
        );

        // Act
        BatteryStatistics stats = batteryService.getBatteryStatistics(batteries);

        // Assert
        assertEquals(1100, stats.getTotalWattCapacity());
        assertEquals(550.0, stats.getAverageWattCapacity());
    }
}
