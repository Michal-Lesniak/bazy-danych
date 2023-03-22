package com.db.DBProject.repositories;

import com.db.DBProject.models.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartRepository extends JpaRepository<Part, Long> {
    Optional<Part> findByPartCode(Integer partCode);
}
