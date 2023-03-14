package com.db.DBProject.repositories;

import com.db.DBProject.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUserCode(Integer customerCode);
}
