package com.db.DBProject.repositories;

import com.db.DBProject.models.Appliance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplianceRepository extends JpaRepository<Appliance, Long> {
    Optional<Appliance> findByApplianceCode(Integer code);
}
