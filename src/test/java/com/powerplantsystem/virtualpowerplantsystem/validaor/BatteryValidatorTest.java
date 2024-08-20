package com.powerplantsystem.virtualpowerplantsystem.validaor;

import com.powerplantsystem.virtualpowerplantsystem.entity.Battery;
import com.powerplantsystem.virtualpowerplantsystem.validator.BatteryValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BatteryValidatorTest {

    private BatteryValidator batteryValidator;

    @BeforeEach
    void setUp() {
        batteryValidator = new BatteryValidator();
    }

    @Test
    void testSupports() {
        // Assert
        assertTrue(batteryValidator.supports(List.class));
    }

    @Test
    void testValidateValidBatteries() {
        // Arrange
        List<Battery> batteries = Arrays.asList(
                new Battery(1L, "Battery1", "2000", 500),
                new Battery(2L, "Battery2", "2001", 600)
        );
        Errors errors = new BeanPropertyBindingResult(batteries, "batteries");

        // Act
        batteryValidator.validate(batteries, errors);

        // Assert
        assertEquals(0, errors.getErrorCount());
    }

    @Test
    void testValidateInvalidBatteries() {
        // Arrange
        List<Battery> batteries = Arrays.asList(
                new Battery(1L, "", "2000", 0),  // Invalid: empty name and zero watt capacity
                new Battery(2L, "Battery2", "", 600)  // Invalid: empty postcode
        );
        Errors errors = new BeanPropertyBindingResult(batteries, "batteries");

        // Act
        batteryValidator.validate(batteries, errors);

        // Assert
        assertEquals(3, errors.getErrorCount());
        assertEquals("Watt Capacity can't be zero or negative for battery ", errors.getAllErrors().get(0).getDefaultMessage());
        assertEquals("Battery name can't be empty", errors.getAllErrors().get(1).getDefaultMessage());
        assertEquals("Postcode can't be empty for battery Battery2", errors.getAllErrors().get(2).getDefaultMessage());
    }
}
