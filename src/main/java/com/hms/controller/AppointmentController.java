package com.hms.controller;

import com.hms.model.Appointment;
import com.hms.model.Nurse;
import com.hms.model.Patient;
import com.hms.model.Physician;
import com.hms.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    
    // POST: Add appointment
    @PostMapping
    public ResponseEntity<String> addAppointment(@RequestBody Appointment appointment) {
        appointmentService.addAppointment(appointment);
        return ResponseEntity.ok("Record Created Successfully");
    }

    // GET: All appointments
    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    // GET: Appointments by start date
    @GetMapping("/{startDate}")
    public ResponseEntity<List<Appointment>> getAppointmentsByStartDate(@PathVariable String startDate) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByStartDate(LocalDateTime.parse(startDate)));
    }

    // GET: Patient by appointment ID
    @GetMapping("/patient/{appointmentId}")
    public ResponseEntity<Patient> getPatientByAppointmentId(@PathVariable Integer appointmentId) {
        return appointmentService.getPatientByAppointmentId(appointmentId)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // GET: Physician by appointment ID
    @GetMapping("/physician/{appointmentId}")
    public ResponseEntity<Physician> getPhysicianByAppointmentId(@PathVariable Integer appointmentId) {
        return appointmentService.getPhysicianByAppointmentId(appointmentId)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // GET: Nurse by appointment ID
    @GetMapping("/nurse/{appointmentId}")
    public ResponseEntity<Nurse> getNurseByAppointmentId(@PathVariable Integer appointmentId) {
        return appointmentService.getNurseByAppointmentId(appointmentId)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/room/{appointmentId}")
    public ResponseEntity<Appointment> updateRoom(@PathVariable Integer appointmentId, @RequestBody Appointment request) {
        Appointment updated = appointmentService.updateRoom(appointmentId, request.getExaminationRoom());
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @GetMapping("/date/{patientId}")
    public ResponseEntity<List<LocalDateTime>> getDatesByPatient(@PathVariable Integer patientId) {
        return ResponseEntity.ok(appointmentService.getDatesByPatient(patientId));
    }

    @GetMapping("/patient/physician/{physicianId}")
    public ResponseEntity<List<Integer>> getPatientsByPhysician(@PathVariable Integer physicianId) {
        return ResponseEntity.ok(appointmentService.getPatientsByPhysician(physicianId));
    }

    @GetMapping("/patient/physician/{physicianId}/{date}")
    public ResponseEntity<List<Integer>> getPatientsByPhysicianAndDate(@PathVariable Integer physicianId, @PathVariable String date) {
        return ResponseEntity.ok(appointmentService.getPatientsByPhysicianAndDate(physicianId, LocalDateTime.parse(date)));
    }

    @GetMapping("/patient/physician/{physicianId}/{patientId}")
    public ResponseEntity<Appointment> getPatientByPhysician(@PathVariable Integer physicianId, @PathVariable Integer patientId) {
        return appointmentService.getPatientByPhysician(physicianId, patientId)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/patient/nurse/{nurseId}")
    public ResponseEntity<List<Integer>> getPatientsByNurse(@PathVariable Integer nurseId) {
        return ResponseEntity.ok(appointmentService.getPatientsByNurse(nurseId));
    }

    @GetMapping("/patient/nurse/{nurseId}/{patientId}")
    public ResponseEntity<Appointment> getPatientByNurse(@PathVariable Integer nurseId, @PathVariable Integer patientId) {
        return appointmentService.getPatientByNurse(nurseId, patientId)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/patient/nurse/{nurseId}/{date}")
    public ResponseEntity<List<Integer>> getPatientsByNurseAndDate(@PathVariable Integer nurseId, @PathVariable String date) {
        return ResponseEntity.ok(appointmentService.getPatientsByNurseAndDate(nurseId, LocalDateTime.parse(date)));
    }

    @GetMapping("/room/patient/{patientId}/{date}")
    public ResponseEntity<String> getRoomByPatientAndDate(@PathVariable Integer patientId, @PathVariable String date) {
        return appointmentService.getRoomByPatientAndDate(patientId, LocalDateTime.parse(date))
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/room/physician/{physicianId}/{date}")
    public ResponseEntity<List<String>> getRoomsByPhysicianAndDate(@PathVariable Integer physicianId, @PathVariable String date) {
        return ResponseEntity.ok(appointmentService.getRoomsByPhysicianAndDate(physicianId, LocalDateTime.parse(date)));
    }

    @GetMapping("/room/nurse/{nurseId}/{date}")
    public ResponseEntity<List<String>> getRoomsByNurseAndDate(@PathVariable Integer nurseId, @PathVariable String date) {
        return ResponseEntity.ok(appointmentService.getRoomsByNurseAndDate(nurseId, LocalDateTime.parse(date)));
    }
}