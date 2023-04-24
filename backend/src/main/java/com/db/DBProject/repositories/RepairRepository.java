package com.db.DBProject.repositories;

import com.db.DBProject.models.Customer;
import com.db.DBProject.models.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepairRepository extends JpaRepository<Repair, Long> {
    Optional<Repair> findByRepairCode(Integer repairCode);
    List<Repair> findByCustomer(Customer customer);

    Integer deleteByRepairCode(Integer repairCode);
}
