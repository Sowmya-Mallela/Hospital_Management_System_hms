package com.hms.controller;
import com.hms.model.Physician;
import com.hms.service.PhysicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/physician")
public class PhysicianController {

    @Autowired
    private PhysicianService physicianService;

    @PostMapping
    public ResponseEntity<String> addPhysician(@RequestBody Physician physician) {
        physicianService.addPhysician(physician);
        return ResponseEntity.ok("Record Created Successfully");
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Physician>> getPhysiciansByName(@PathVariable String name) {
        List<Physician> physicians = physicianService.getPhysiciansByName(name);
        if (!physicians.isEmpty()) {
            return ResponseEntity.ok(physicians);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/position/{pos}")
    public ResponseEntity<List<Physician>> getPhysiciansByPosition(@PathVariable String pos) {
        List<Physician> physicians = physicianService.getPhysiciansByPosition(pos);
        return ResponseEntity.ok(physicians);
    }

    @GetMapping("/empid/{empid}")
    public ResponseEntity<Physician> getPhysicianById(@PathVariable Integer empid) {
        return physicianService.getPhysicianById(empid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/position/{position}/{employeeId}")
    public ResponseEntity<?> updatePosition(@PathVariable String position, @PathVariable Integer employeeId) {
        Physician updated = physicianService.updatePosition(employeeId, position);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.badRequest().body("{\"timeStamp\":\"2023-06-08\", \"message\": \"Validation failed\"}");
        }
    }

    @PutMapping("/update/name/{empid}")
    public ResponseEntity<?> updateName(@PathVariable Integer empid, @RequestBody Physician request) {
        Physician updated = physicianService.updateName(empid, request.getName());
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.badRequest().body("{\"timeStamp\":\"2023-06-08\", \"message\": \"Validation failed\"}");
        }
    }

    @PutMapping("/update/ssn/{empid}")
    public ResponseEntity<?> updateSsn(@PathVariable Integer empid, @RequestBody Physician request) {
        Physician updated = physicianService.updateSsn(empid, request.getSsn());
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.badRequest().body("{\"timeStamp\":\"2023-06-08\", \"message\": \"Validation failed\"}");
        }
    }
}