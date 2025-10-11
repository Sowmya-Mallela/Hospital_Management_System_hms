package com.hms.controller;

import com.hms.model.Procedure;
import com.hms.service.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/procedure")
public class ProcedureController {

    @Autowired
    private ProcedureService procedureService;

    @PostMapping
    public ResponseEntity<String> addProcedure(@RequestBody Procedure procedure) {
        procedureService.saveProcedure(procedure);
        return ResponseEntity.ok("Record Created Successfully");
    }

    @GetMapping
    public ResponseEntity<List<Procedure>> getAllProcedures() {
        return ResponseEntity.ok(procedureService.getAllProcedures());
    }

    @GetMapping("/cost/{id}")
    public ResponseEntity<Procedure> getProcedureById(@PathVariable Integer id) {
        return procedureService.getProcedureById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cost/name/{name}")
    public ResponseEntity<Procedure> getProcedureByName(@PathVariable String name) {
        return procedureService.getProcedureByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/cost/id")
    public ResponseEntity<Procedure> updateCost(@RequestParam Integer id, @RequestParam Double cost) {
        return procedureService.updateCost(id, cost)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/name/{id}")
    public ResponseEntity<Procedure> updateName(@PathVariable Integer id, @RequestBody Procedure request) {
        return procedureService.updateName(id, request.getName())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}