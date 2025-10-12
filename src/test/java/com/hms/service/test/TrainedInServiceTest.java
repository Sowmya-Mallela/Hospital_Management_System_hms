package com.hms.service.test;

import com.hms.model.TrainedIn;
import com.hms.model.TrainedInId;
import com.hms.repository.TrainedInRepository;
import com.hms.service.TrainedInService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrainedInServiceTest {

    @Mock
    private TrainedInRepository trainedInRepository;

    @InjectMocks
    private TrainedInService trainedInService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddCertification_New() {
        TrainedInId id = new TrainedInId(101, 201);
        TrainedIn trainedIn = new TrainedIn();
        trainedIn.setId(id);
        trainedIn.setCertificationDate(LocalDateTime.now());
        trainedIn.setCertificationExpires(LocalDateTime.now().plusYears(1));

        when(trainedInRepository.existsById(id)).thenReturn(false);
        when(trainedInRepository.save(trainedIn)).thenReturn(trainedIn);

        boolean result = trainedInService.addCertification(trainedIn);
        assertTrue(result);
        verify(trainedInRepository, times(1)).save(trainedIn);
    }

    @Test
    public void testAddCertification_AlreadyExists() {
        TrainedInId id = new TrainedInId(101, 201);
        TrainedIn trainedIn = new TrainedIn();
        trainedIn.setId(id);

        when(trainedInRepository.existsById(id)).thenReturn(true);

        boolean result = trainedInService.addCertification(trainedIn);
        assertFalse(result);
        verify(trainedInRepository, never()).save(any());
    }

    @Test
    public void testGetAllCertifications() {
        List<TrainedIn> certifications = List.of(new TrainedIn(), new TrainedIn());
        when(trainedInRepository.findAll()).thenReturn(certifications);

        List<TrainedIn> result = trainedInService.getAllCertifications();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetTreatmentsByPhysician() {
        List<TrainedIn> treatments = List.of(new TrainedIn());
        when(trainedInRepository.findByIdPhysician(101)).thenReturn(treatments);

        List<TrainedIn> result = trainedInService.getTreatmentsByPhysician(101);
        assertEquals(1, result.size());
    }

    @Test
    public void testGetPhysiciansByTreatment() {
        List<TrainedIn> physicians = List.of(new TrainedIn());
        when(trainedInRepository.findByIdTreatment(201)).thenReturn(physicians);

        List<TrainedIn> result = trainedInService.getPhysiciansByTreatment(201);
        assertEquals(1, result.size());
    }

    @Test
    public void testGetExpiringCertifications() {
        // Create a TrainedIn object with expiry within 1 month
        TrainedIn trainedIn = new TrainedIn();
        TrainedInId id = new TrainedInId(101, 201);
        trainedIn.setId(id);
        trainedIn.setCertificationDate(LocalDateTime.now().minusMonths(6));
        trainedIn.setCertificationExpires(LocalDateTime.now().plusDays(10)); // within 1 month

        List<TrainedIn> expiringCerts = List.of(trainedIn);

        // Mock repository call — match service logic
        when(trainedInRepository.findByIdPhysicianAndCertificationExpiresBefore(
                eq(101), any(LocalDateTime.class)))
            .thenReturn(expiringCerts);

        // Call service method
        List<TrainedIn> result = trainedInService.getExpiringCertifications(101);

        // Assert
        assertEquals(1, result.size());
        assertEquals(id, result.get(0).getId());
    }

    @Test
    public void testUpdateCertificationExpiry_Success() {
        TrainedInId id = new TrainedInId(101, 201);
        TrainedIn trainedIn = new TrainedIn();
        trainedIn.setId(id);

        when(trainedInRepository.findById(id)).thenReturn(Optional.of(trainedIn));
        when(trainedInRepository.save(any())).thenReturn(trainedIn);

        boolean result = trainedInService.updateCertificationExpiry(id, LocalDateTime.now().plusYears(2));
        assertTrue(result);
    }

    @Test
    public void testUpdateCertificationExpiry_Failure() {
        TrainedInId id = new TrainedInId(101, 201);
        when(trainedInRepository.findById(id)).thenReturn(Optional.empty());

        boolean result = trainedInService.updateCertificationExpiry(id, LocalDateTime.now().plusYears(2));
        assertFalse(result);
    }
}