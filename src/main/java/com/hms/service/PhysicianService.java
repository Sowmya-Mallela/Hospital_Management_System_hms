package com.hms.service;

import com.hms.model.Physician;
import com.hms.repository.PhysicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhysicianService {

    @Autowired
    private PhysicianRepository physicianRepository;

    public Physician addPhysician(Physician physician) {
        return physicianRepository.save(physician);
    }

    public List<Physician> getPhysiciansByName(String name) {
        return physicianRepository.findByName(name);
    }

    public List<Physician> getPhysiciansByPosition(String position) {
        return physicianRepository.findByPosition(position);
    }

    public Optional<Physician> getPhysicianById(Integer empid) {
        return physicianRepository.findById(empid);
    }

    public Physician updatePosition(Integer empid, String position) {
        Optional<Physician> optional = physicianRepository.findById(empid);
        if (optional.isPresent()) {
            Physician physician = optional.get();
            physician.setPosition(position);
            return physicianRepository.save(physician);
        }
        return null;
    }

    public Physician updateName(Integer empid, String name) {
        Optional<Physician> optional = physicianRepository.findById(empid);
        if (optional.isPresent()) {
            Physician physician = optional.get();
            physician.setName(name);
            return physicianRepository.save(physician);
        }
        return null;
    }

    public Physician updateSsn(Integer empid, Integer ssn) {
        Optional<Physician> optional = physicianRepository.findById(empid);
        if (optional.isPresent()) {
            Physician physician = optional.get();
            physician.setSsn(ssn);
            return physicianRepository.save(physician);
        }
        return null;
    }
}