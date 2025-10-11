package com.hms.repository;

import com.hms.model.TrainedIn;
import com.hms.model.TrainedInId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TrainedInRepository extends JpaRepository<TrainedIn, TrainedInId> {
    List<TrainedIn> findByIdPhysician(Integer physician);
    List<TrainedIn> findByIdTreatment(Integer treatment);
    List<TrainedIn> findByIdPhysicianAndCertificationExpiresBefore(Integer physician, LocalDateTime expiry);
}
