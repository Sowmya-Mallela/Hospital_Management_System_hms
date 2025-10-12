package com.hms.service.test;

import com.hms.model.Patient;
import com.hms.repository.PatientRepository;
import com.hms.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddPatient() {
        Patient patient = new Patient(101, "Lakshmi", "Chennai", "9876543210", 555, 1);
        when(patientRepository.save(patient)).thenReturn(patient);

        assertDoesNotThrow(() -> patientService.addPatient(patient));
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    public void testGetAllPatients() {
        List<Patient> patients = Arrays.asList(
                new Patient(101, "Lakshmi", "Chennai", "9876543210", 555, 1),
                new Patient(102, "Sowmya", "Bangalore", "9123456780", 556, 2)
        );
        when(patientRepository.findAll()).thenReturn(patients);

        List<Patient> result = patientService.getAllPatients();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetPatientById() {
        Patient patient = new Patient(101, "Lakshmi", "Chennai", "9876543210", 555, 1);
        when(patientRepository.findById(101)).thenReturn(Optional.of(patient));

        Optional<Patient> result = patientService.getPatientById(101);
        assertTrue(result.isPresent());
        assertEquals("Lakshmi", result.get().getName());
    }

    @Test
    public void testGetPatientsByPhysician() {
        List<Patient> patients = List.of(new Patient(101, "Lakshmi", "Chennai", "9876543210", 555, 1));
        when(patientRepository.findByPcp(1)).thenReturn(patients);

        List<Patient> result = patientService.getPatientsByPhysician(1);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getPcp());
    }

    @Test
    public void testGetPatientByPhysicianAndId() {
        Patient patient = new Patient(101, "Lakshmi", "Chennai", "9876543210", 555, 1);
        when(patientRepository.findByPcpAndSsn(1, 101)).thenReturn(Optional.of(patient));

        Optional<Patient> result = patientService.getPatientByPhysicianAndId(1, 101);
        assertTrue(result.isPresent());
        assertEquals("Lakshmi", result.get().getName());
    }

    @Test
    public void testUpdateAddress() {
        Patient patient = new Patient(101, "Lakshmi", "Old Address", "9876543210", 555, 1);
        when(patientRepository.findById(101)).thenReturn(Optional.of(patient));
        when(patientRepository.save(any())).thenReturn(patient);

        Patient updated = patientService.updateAddress(101, "New Address");
        assertNotNull(updated);
        assertEquals("New Address", updated.getAddress());
    }

    @Test
    public void testUpdatePhone() {
        Patient patient = new Patient(101, "Lakshmi", "Chennai", "9876543210", 555, 1);
        when(patientRepository.findById(101)).thenReturn(Optional.of(patient));
        when(patientRepository.save(any())).thenReturn(patient);

        Patient updated = patientService.updatePhone(101, "9123456780");
        assertNotNull(updated);
        assertEquals("9123456780", updated.getPhone());
    }
}
