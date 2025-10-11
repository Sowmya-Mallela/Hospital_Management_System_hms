package com.hms.service;

import com.hms.model.Patient;
import com.hms.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public void addPatient(Patient patient) {
        patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Integer ssn) {
        return patientRepository.findById(ssn);
    }

    public List<Patient> getPatientsByPhysician(Integer physicianId) {
        return patientRepository.findByPcp(physicianId);
    }

    public Optional<Patient> getPatientByPhysicianAndId(Integer physicianId, Integer patientId) {
        return patientRepository.findByPcpAndSsn(physicianId, patientId);
    }

    public Patient updateAddress(Integer ssn, String newAddress) {
        return patientRepository.findById(ssn).map(p -> {
            p.setAddress(newAddress);
            return patientRepository.save(p);
        }).orElse(null);
    }

    public Patient updatePhone(Integer ssn, String newPhone) {
        return patientRepository.findById(ssn).map(p -> {
            p.setPhone(newPhone);
            return patientRepository.save(p);
        }).orElse(null);
    }
}
