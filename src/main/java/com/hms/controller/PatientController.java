package com.hms.controller;

import com.hms.model.Patient;
import com.hms.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<String> addPatient(@RequestBody Patient patient) {
        try {
            patientService.addPatient(patient);
            return ResponseEntity.ok("Record Created Successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/{physicianid}")
    public ResponseEntity<List<Patient>> getPatientsByPhysician(@PathVariable Integer physicianid) {
        return ResponseEntity.ok(patientService.getPatientsByPhysician(physicianid));
    }

    @GetMapping("/{physicianid}/{patientid}")
    public ResponseEntity<Patient> getPatientByPhysicianAndId(@PathVariable Integer physicianid,
                                                              @PathVariable Integer patientid) {
        Optional<Patient> patient = patientService.getPatientByPhysicianAndId(physicianid, patientid);
        return patient.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/insurance/{patientid}")
    public ResponseEntity<Integer> getInsuranceId(@PathVariable Integer patientid) {
        Optional<Patient> patient = patientService.getPatientById(patientid);
        return patient.map(p -> ResponseEntity.ok(p.getInsuranceId()))
                      .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/address/{ssn}")
    public ResponseEntity<Patient> updateAddress(@PathVariable Integer ssn, @RequestBody Patient request) {
        Patient updated = patientService.updateAddress(ssn, request.getAddress());
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @PutMapping("/phone/{ssn}")
    public ResponseEntity<Patient> updatePhone(@PathVariable Integer ssn, @RequestBody Patient request) {
        Patient updated = patientService.updatePhone(ssn, request.getPhone());
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
}
