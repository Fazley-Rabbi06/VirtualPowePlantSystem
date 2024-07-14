package com.powerplantsystem.virtualpowerplantsystem.repository;

import com.powerplantsystem.virtualpowerplantsystem.entity.Battery;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BatteryRepository extends JpaRepository<Battery, Long> {
    List<Battery> findByPostcodeBetween(String postcodeStart, String postcodeEnd);
}
