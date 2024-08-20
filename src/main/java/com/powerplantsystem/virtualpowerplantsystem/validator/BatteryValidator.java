package com.powerplantsystem.virtualpowerplantsystem.validator;

import com.powerplantsystem.virtualpowerplantsystem.entity.Battery;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class BatteryValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return List.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        List<Battery> batteryList = (List<Battery>) target;

        IntStream.range(0, batteryList.size()).forEach(index -> {
            Battery battery = batteryList.get(index);
            if (battery.getWattCapacity() <= 0) {
                errors.reject("batteryList[" + index + "].wattCapacity", "Watt Capacity can't be zero or negative for battery " + battery.getName());
            }
            if (battery.getName() == null || battery.getName().isEmpty()) {
                errors.reject("batteryList[" + index + "].name", "Battery name can't be empty");
            }
            if (battery.getPostcode() == null || battery.getPostcode().isEmpty()) {
                errors.reject("batteryList[" + index + "].postcode", "Postcode can't be empty for battery " + battery.getName());
            }
        });
    }
}
