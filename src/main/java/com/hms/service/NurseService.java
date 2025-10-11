package com.hms.service;

import com.hms.model.Nurse;
import com.hms.repository.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NurseService {

    @Autowired
    private NurseRepository nurseRepository;

    public Nurse addNurse(Nurse nurse) {
        return nurseRepository.save(nurse);
    }

    public List<Nurse> getAllNurses() {
        return nurseRepository.findAll();
    }

    public Optional<Nurse> getNurseById(Integer empId) {
        return nurseRepository.findById(empId);
    }

    public String getPositionById(Integer empId) {
        return nurseRepository.findById(empId)
                .map(Nurse::getPosition)
                .orElse(null);
    }

    public Boolean isRegistered(Integer empId) {
        return nurseRepository.findById(empId)
                .map(Nurse::getRegistered)
                .orElse(null);
    }

    public Nurse updateRegistered(Integer empId, Boolean registered) {
        return nurseRepository.findById(empId).map(nurse -> {
            nurse.setRegistered(registered);
            return nurseRepository.save(nurse);
        }).orElse(null);
    }

    public Nurse updateSsn(Integer empId, Integer ssn) {
        return nurseRepository.findById(empId).map(nurse -> {
            nurse.setSsn(ssn);
            return nurseRepository.save(nurse);
        }).orElse(null);
    }
}