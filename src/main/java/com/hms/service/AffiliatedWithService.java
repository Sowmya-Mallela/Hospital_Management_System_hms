package com.hms.service;

import com.hms.model.AffiliatedWith;
import com.hms.repository.AffiliatedWithRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AffiliatedWithService {

    @Autowired
    private AffiliatedWithRepository affiliatedWithRepository;

    public List<AffiliatedWith> getPhysiciansByDepartment(Integer departmentId) {
        return affiliatedWithRepository.findByIdDepartment(departmentId);
    }

    public List<AffiliatedWith> getDepartmentsByPhysician(Integer physicianId) {
        return affiliatedWithRepository.findByIdPhysician(physicianId);
    }

    public long countPhysiciansInDepartment(Integer deptId) {
        return affiliatedWithRepository.countByIdDepartment(deptId);
    }

    public boolean hasPrimaryAffiliation(Integer physicianId) {
        return affiliatedWithRepository.findFirstByIdPhysicianAndPrimaryAffiliationTrue(physicianId) != null;
    }

    public boolean updatePrimaryAffiliation(Integer physicianId) {
        List<AffiliatedWith> affiliations = affiliatedWithRepository.findByIdPhysician(physicianId);
        if (affiliations.isEmpty()) return false;

        for (AffiliatedWith aff : affiliations) {
            aff.setPrimaryAffiliation(false);
        }

        affiliations.get(0).setPrimaryAffiliation(true);
        affiliatedWithRepository.saveAll(affiliations);
        return true;
    }

    public String addAffiliation(AffiliatedWith affiliatedWith) {
        affiliatedWithRepository.save(affiliatedWith);
        return "Record Created Successfully";
    }
}
