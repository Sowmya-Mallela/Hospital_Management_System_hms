package com.hms.service.test;

import com.hms.model.Physician;
import com.hms.repository.PhysicianRepository;
import com.hms.service.PhysicianService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PhysicianServiceTest {

    @Mock
    private PhysicianRepository physicianRepository;

    @InjectMocks
    private PhysicianService physicianService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    public void testAddPhysician() {
        Physician physician = new Physician(101, "Dr. Lakshmi", "Cardiologist", 123456789);
        when(physicianRepository.save(physician)).thenReturn(physician);

        Physician saved = physicianService.addPhysician(physician);
        assertNotNull(saved);
        assertEquals("Dr. Lakshmi", saved.getName());
    }

    @Test
    public void testGetPhysicianById() {
        Physician physician = new Physician(101, "Dr. Lakshmi", "Cardiologist", 123456789);
        when(physicianRepository.findById(101)).thenReturn(Optional.of(physician));

        Optional<Physician> result = physicianService.getPhysicianById(101);
        assertTrue(result.isPresent());
        assertEquals(101, result.get().getEmployeeId());
    }

    @Test
    public void testUpdatePosition() {
        Physician physician = new Physician(101, "Dr. Lakshmi", "Cardiologist", 123456789);
        when(physicianRepository.findById(101)).thenReturn(Optional.of(physician));
        when(physicianRepository.save(any())).thenReturn(physician);

        Physician updated = physicianService.updatePosition(101, "Neurologist");
        assertNotNull(updated);
        assertEquals("Neurologist", updated.getPosition());
    }
}
