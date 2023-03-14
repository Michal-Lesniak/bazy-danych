package com.db.DBProject.repositories;

import com.db.DBProject.models.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairRepository extends JpaRepository<Repair, Long> {
}
