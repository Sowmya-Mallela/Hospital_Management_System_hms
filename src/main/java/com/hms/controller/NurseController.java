package com.hms.controller;

import com.hms.model.Nurse;
import com.hms.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nurse")
public class NurseController {

    @Autowired
    private NurseService nurseService;

    @PostMapping
    public ResponseEntity<String> addNurse(@RequestBody Nurse nurse) {
        nurseService.addNurse(nurse);
        return ResponseEntity.ok("Record Created Successfully");
    }

    @GetMapping
    public ResponseEntity<List<Nurse>> getAllNurses() {
        return ResponseEntity.ok(nurseService.getAllNurses());
    }

    @GetMapping("/{empid}")
    public ResponseEntity<Nurse> getNurseById(@PathVariable Integer empid) {
        return nurseService.getNurseById(empid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/position/{empid}")
    public ResponseEntity<String> getPosition(@PathVariable Integer empid) {
        String position = nurseService.getPositionById(empid);
        return position != null ? ResponseEntity.ok(position) : ResponseEntity.notFound().build();
    }

    @GetMapping("/registered/{empid}")
    public ResponseEntity<Boolean> isRegistered(@PathVariable Integer empid) {
        Boolean registered = nurseService.isRegistered(empid);
        return registered != null ? ResponseEntity.ok(registered) : ResponseEntity.notFound().build();
    }

    @PutMapping("/registered/{empid}")
    public ResponseEntity<Nurse> updateRegistered(@PathVariable Integer empid, @RequestBody Nurse request) {
        Nurse updated = nurseService.updateRegistered(empid, request.getRegistered());
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @PutMapping("/ssn/{empid}")
    public ResponseEntity<Nurse> updateSsn(@PathVariable Integer empid, @RequestBody Nurse request) {
        Nurse updated = nurseService.updateSsn(empid, request.getSsn());
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
}
