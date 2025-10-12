package com.hms.service.test;

import com.hms.model.Appointment;
import com.hms.model.Patient;
import com.hms.model.Physician;
import com.hms.model.Nurse;
import com.hms.repository.AppointmentRepository;
import com.hms.repository.PatientRepository;
import com.hms.repository.PhysicianRepository;
import com.hms.repository.NurseRepository;
import com.hms.service.AppointmentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PhysicianRepository physicianRepository;

    @Mock
    private NurseRepository nurseRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddAppointment() {
        Appointment appointment = new Appointment(101, 201, 301,
                LocalDateTime.of(2025, 10, 12, 10, 0),
                LocalDateTime.of(2025, 10, 12, 11, 0),
                "Room A");

        when(appointmentRepository.save(appointment)).thenReturn(appointment);

        assertDoesNotThrow(() -> appointmentService.addAppointment(appointment));
        verify(appointmentRepository, times(1)).save(appointment);
    }

    @Test
    public void testGetAllAppointments() {
        List<Appointment> appointments = List.of(
                new Appointment(101, 201, 301, LocalDateTime.now(), LocalDateTime.now().plusHours(1), "Room A")
        );
        when(appointmentRepository.findAll()).thenReturn(appointments);

        List<Appointment> result = appointmentService.getAllAppointments();
        assertEquals(1, result.size());
    }

    @Test
    public void testGetAppointmentsByStartDate() {
        LocalDateTime startDate = LocalDateTime.of(2025, 10, 12, 10, 0);
        List<Appointment> appointments = List.of(
                new Appointment(101, 201, 301, startDate, startDate.plusHours(1), "Room A")
        );
        when(appointmentRepository.findByStarto(startDate)).thenReturn(appointments);

        List<Appointment> result = appointmentService.getAppointmentsByStartDate(startDate);
        assertEquals(1, result.size());
        assertEquals(startDate, result.get(0).getStarto());
    }

    @Test
    public void testUpdateRoom() {
        Appointment appointment = new Appointment(101, 201, 301,
                LocalDateTime.now(), LocalDateTime.now().plusHours(1), "Room A");
        appointment.setAppointmentID(1);

        when(appointmentRepository.findById(1)).thenReturn(Optional.of(appointment));
        when(appointmentRepository.save(any())).thenReturn(appointment);

        Appointment updated = appointmentService.updateRoom(1, "Room B");
        assertNotNull(updated);
        assertEquals("Room B", updated.getExaminationRoom());
    }

    @Test
    public void testGetPatientByAppointmentId() {
        Appointment appointment = new Appointment(101, 201, 301,
                LocalDateTime.now(), LocalDateTime.now().plusHours(1), "Room A");
        appointment.setAppointmentID(1);

        Patient patient = new Patient(101, "Lakshmi", "Chennai", "9876543210", 555, 301);

        when(appointmentRepository.findById(1)).thenReturn(Optional.of(appointment));
        when(patientRepository.findById(101)).thenReturn(Optional.of(patient));

        Optional<Patient> result = appointmentService.getPatientByAppointmentId(1);
        assertTrue(result.isPresent());
        assertEquals("Lakshmi", result.get().getName());
    }
}