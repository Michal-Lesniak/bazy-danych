package com.db.DBProject.repositories;

import com.db.DBProject.models.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {
}
