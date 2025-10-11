package com.hms.controller;

import com.hms.model.TrainedIn;
import com.hms.model.TrainedInId;
import com.hms.service.TrainedInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/trained_in")
public class TrainedInController {

    @Autowired
    private TrainedInService trainedInService;

    @PostMapping
    public ResponseEntity<String> addCertification(@RequestBody TrainedIn trainedIn) {
        boolean success = trainedInService.addCertification(trainedIn);
        if (!success) {
            return ResponseEntity.badRequest().body("Certification already exists.");
        }
        return ResponseEntity.ok("Record Created Successfully");
    }

    @GetMapping
    public ResponseEntity<List<TrainedIn>> getAllCertifications() {
        return ResponseEntity.ok(trainedInService.getAllCertifications());
    }

    @GetMapping("/treatment/{physicianid}")
    public ResponseEntity<List<TrainedIn>> getTreatmentsByPhysician(@PathVariable Integer physicianid) {
        return ResponseEntity.ok(trainedInService.getTreatmentsByPhysician(physicianid));
    }

    @GetMapping("/physicians/{procedureid}")
    public ResponseEntity<List<TrainedIn>> getPhysiciansByTreatment(@PathVariable Integer procedureid) {
        return ResponseEntity.ok(trainedInService.getPhysiciansByTreatment(procedureid));
    }

    @GetMapping("/expiredsooncerti/{physicianid}")
    public ResponseEntity<List<TrainedIn>> getExpiringCertifications(@PathVariable Integer physicianid) {
        return ResponseEntity.ok(trainedInService.getExpiringCertifications(physicianid));
    }

    @PutMapping("/certificationexpiry")
    public ResponseEntity<Boolean> updateCertificationExpiry(@RequestBody TrainedInId id,
                                                             @RequestParam String expiryDateTime) {
        LocalDateTime expiry = LocalDateTime.parse(expiryDateTime);
        boolean updated = trainedInService.updateCertificationExpiry(id, expiry);
        return ResponseEntity.ok(updated);
    }
}