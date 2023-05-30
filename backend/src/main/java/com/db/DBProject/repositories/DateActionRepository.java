package com.db.DBProject.repositories;

import com.db.DBProject.models.DateAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DateActionRepository extends JpaRepository<DateAction, Long> {
    Optional<DateAction> findByDateCode(Integer dateCode);
}
