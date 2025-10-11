package com.hms.service;

import com.hms.model.Department;
import com.hms.model.Physician;
import com.hms.model.TrainedIn;
import com.hms.repository.DepartmentRepository;
import com.hms.repository.PhysicianRepository;
import com.hms.repository.TrainedInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PhysicianRepository physicianRepository;

    @Autowired
    private TrainedInRepository trainedInRepository;

    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Integer deptId) {
        return departmentRepository.findById(deptId);
    }

    public Optional<Physician> getHeadOfDepartment(Integer deptId) {
        Optional<Department> dept = departmentRepository.findById(deptId);
        if (dept.isPresent()) {
            Integer headId = dept.get().getHead();
            return physicianRepository.findById(headId);
        }
        return Optional.empty();
    }

    public Optional<List<TrainedIn>> getHeadCertifications(Integer deptId) {
        Optional<Department> dept = departmentRepository.findById(deptId);
        if (dept.isPresent()) {
            Integer headId = dept.get().getHead();
            List<TrainedIn> certifications = trainedInRepository.findByIdPhysician(headId);
            if (certifications == null || certifications.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(certifications);
        }
        return Optional.empty();
    }

    public List<Department> getDepartmentsByHeadId(Integer headId) {
        return departmentRepository.findAll().stream()
                .filter(dept -> dept.getHead() != null && dept.getHead().equals(headId))
                .collect(Collectors.toList());
    }

    public boolean isPhysicianHead(Integer physicianId) {
        return departmentRepository.findAll().stream()
                .anyMatch(dept -> dept.getHead() != null && dept.getHead().equals(physicianId));
    }

    public Department updateDepartmentHead(Integer deptId, Integer newHeadId) {
        return departmentRepository.findById(deptId).map(dept -> {
            dept.setHead(newHeadId);
            return departmentRepository.save(dept);
        }).orElse(null);
    }

    public Department updateDepartmentName(Integer deptId, String newName) {
        return departmentRepository.findById(deptId).map(dept -> {
            dept.setDeptName(newName);
            return departmentRepository.save(dept);
        }).orElse(null);
    }
}