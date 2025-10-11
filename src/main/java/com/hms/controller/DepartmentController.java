package com.hms.controller;

import com.hms.model.Department;
import com.hms.model.Physician;
import com.hms.model.TrainedIn;
import com.hms.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<String> addDepartment(@RequestBody Department department) {
        departmentService.addDepartment(department);
        return ResponseEntity.ok("Record Created Successfully");
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{deptid}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Integer deptid) {
        return departmentService.getDepartmentById(deptid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/head/{deptid}")
    public ResponseEntity<Physician> getHeadOfDepartment(@PathVariable Integer deptid) {
        return departmentService.getHeadOfDepartment(deptid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/headcertification/{deptid}")
    public ResponseEntity<List<TrainedIn>> getHeadCertifications(@PathVariable Integer deptid) {
        return departmentService.getHeadCertifications(deptid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{head}")
    public ResponseEntity<List<Department>> getDepartmentsByHead(@PathVariable Integer head) {
        return ResponseEntity.ok(departmentService.getDepartmentsByHeadId(head));
    }

    @GetMapping("/check/{physicianid}")
    public ResponseEntity<Boolean> isPhysicianHead(@PathVariable Integer physicianid) {
        return ResponseEntity.ok(departmentService.isPhysicianHead(physicianid));
    }

    @PutMapping
    public ResponseEntity<Department> updateDepartmentHead(@RequestParam Integer deptid, @RequestParam Integer headid) {
        Department updated = departmentService.updateDepartmentHead(deptid, headid);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @PutMapping("/update/deptname/{deptid}")
    public ResponseEntity<Department> updateDepartmentName(@PathVariable Integer deptid, @RequestBody Department request) {
        Department updated = departmentService.updateDepartmentName(deptid, request.getDeptName());
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
}