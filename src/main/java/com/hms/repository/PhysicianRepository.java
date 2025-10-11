package com.hms.repository;

import com.hms.model.Physician;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhysicianRepository extends JpaRepository<Physician, Integer> {

    List<Physician> findByName(String name);

    List<Physician> findByPosition(String position);

    Optional<Physician> findOneByName(String name);
}