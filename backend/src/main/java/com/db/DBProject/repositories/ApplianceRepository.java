package com.db.DBProject.repositories;

import com.db.DBProject.models.Appliance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplianceRepository extends JpaRepository<Appliance, Long> {
}
