package com.hms.service;

import com.hms.model.TrainedIn;
import com.hms.model.TrainedInId;
import com.hms.repository.TrainedInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrainedInService {

    @Autowired
    private TrainedInRepository trainedInRepository;

    public boolean addCertification(TrainedIn trainedIn) {
        if (trainedInRepository.existsById(trainedIn.getId())) {
            return false;
        }
        trainedInRepository.save(trainedIn);
        return true;
    }

    public List<TrainedIn> getAllCertifications() {
        return trainedInRepository.findAll();
    }

    public List<TrainedIn> getTreatmentsByPhysician(Integer physicianId) {
        return trainedInRepository.findByIdPhysician(physicianId);
    }

    public List<TrainedIn> getPhysiciansByTreatment(Integer procedureId) {
        return trainedInRepository.findByIdTreatment(procedureId);
    }

    public List<TrainedIn> getExpiringCertifications(Integer physicianId) {
        LocalDateTime oneMonthLater = LocalDateTime.now().plusMonths(1);
        return trainedInRepository.findByIdPhysicianAndCertificationExpiresBefore(physicianId, oneMonthLater);
    }

    public boolean updateCertificationExpiry(TrainedInId id, LocalDateTime newExpiry) {
        return trainedInRepository.findById(id).map(cert -> {
            cert.setCertificationExpires(newExpiry);
            trainedInRepository.save(cert);
            return true;
        }).orElse(false);
    }
}