package com.hms.service;

import com.hms.model.Procedure;
import com.hms.repository.ProcedureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcedureService {

    @Autowired
    private ProcedureRepository procedureRepository;

    public void saveProcedure(Procedure procedure) {
        procedureRepository.save(procedure);
    }

    public List<Procedure> getAllProcedures() {
        return procedureRepository.findAll();
    }

    public Optional<Procedure> getProcedureById(Integer id) {
        return procedureRepository.findById(id);
    }

    public Optional<Procedure> getProcedureByName(String name) {
        return procedureRepository.findByName(name);
    }

    public Optional<Procedure> updateCost(Integer id, Double newCost) {
        Optional<Procedure> optional = procedureRepository.findById(id);
        optional.ifPresent(p -> {
            p.setCost(newCost);
            procedureRepository.save(p);
        });
        return optional;
    }

    public Optional<Procedure> updateName(Integer id, String newName) {
        Optional<Procedure> optional = procedureRepository.findById(id);
        optional.ifPresent(p -> {
            p.setName(newName);
            procedureRepository.save(p);
        });
        return optional;
    }
}