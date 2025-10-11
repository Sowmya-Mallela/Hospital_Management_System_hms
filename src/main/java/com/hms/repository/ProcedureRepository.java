package com.hms.repository;

import com.hms.model.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProcedureRepository extends JpaRepository<Procedure, Integer> {
    Optional<Procedure> findByName(String name);
}