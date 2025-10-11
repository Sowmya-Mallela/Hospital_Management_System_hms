
package com.hms.repository;

import com.hms.model.AffiliatedWith;
import com.hms.model.AffiliatedWithId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AffiliatedWithRepository extends JpaRepository<AffiliatedWith, AffiliatedWithId> {
    List<AffiliatedWith> findByIdDepartment(Integer departmentId);
    List<AffiliatedWith> findByIdPhysician(Integer physicianId);
    long countByIdDepartment(Integer departmentId);
    AffiliatedWith findFirstByIdPhysicianAndPrimaryAffiliationTrue(Integer physicianId);
}