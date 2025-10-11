package com.hms.repository;

import com.hms.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    List<Patient> findByPcp(Integer physicianId);
    Optional<Patient> findByPcpAndSsn(Integer physicianId, Integer ssn);
}