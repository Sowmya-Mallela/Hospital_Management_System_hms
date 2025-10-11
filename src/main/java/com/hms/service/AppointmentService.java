package com.hms.service;

import com.hms.model.Appointment;
import com.hms.model.Patient;
import com.hms.model.Physician;
import com.hms.model.Nurse;
import com.hms.repository.AppointmentRepository;
import com.hms.repository.PatientRepository;
import com.hms.repository.PhysicianRepository;
import com.hms.repository.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PhysicianRepository physicianRepository;

    @Autowired
    private NurseRepository nurseRepository;

    public void addAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentsByStartDate(LocalDateTime startDate) {
        return appointmentRepository.findByStarto(startDate);
    }

    public Optional<Patient> getPatientByAppointmentId(Integer appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .flatMap(app -> patientRepository.findById(app.getPatient()));
    }

    public Optional<Physician> getPhysicianByAppointmentId(Integer appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .flatMap(app -> physicianRepository.findById(app.getPhysician()));
    }

    public Optional<Nurse> getNurseByAppointmentId(Integer appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .flatMap(app -> nurseRepository.findById(app.getPrepNurse()));
    }

    public Appointment updateRoom(Integer appointmentId, String room) {
        return appointmentRepository.findById(appointmentId).map(app -> {
            app.setExaminationRoom(room);
            return appointmentRepository.save(app);
        }).orElse(null);
    }

    public List<LocalDateTime> getDatesByPatient(Integer patientId) {
        return appointmentRepository.findByPatient(patientId).stream()
                .map(Appointment::getStarto).collect(Collectors.toList());
    }

    public List<Integer> getPatientsByPhysician(Integer physicianId) {
        return appointmentRepository.findByPhysician(physicianId).stream()
                .map(Appointment::getPatient).collect(Collectors.toList());
    }

    public List<Integer> getPatientsByPhysicianAndDate(Integer physicianId, LocalDateTime date) {
        return appointmentRepository.findByPhysicianAndStarto(physicianId, date).stream()
                .map(Appointment::getPatient).collect(Collectors.toList());
    }

    public Optional<Appointment> getPatientByPhysician(Integer physicianId, Integer patientId) {
        return appointmentRepository.findByPhysicianAndPatient(physicianId, patientId);
    }

    public List<Integer> getPatientsByNurse(Integer nurseId) {
        return appointmentRepository.findByPrepNurse(nurseId).stream()
                .map(Appointment::getPatient).collect(Collectors.toList());
    }

    public Optional<Appointment> getPatientByNurse(Integer nurseId, Integer patientId) {
        return appointmentRepository.findByPrepNurseAndPatient(nurseId, patientId);
    }

    public List<Integer> getPatientsByNurseAndDate(Integer nurseId, LocalDateTime date) {
        return appointmentRepository.findByPrepNurseAndStarto(nurseId, date).stream()
                .map(Appointment::getPatient).collect(Collectors.toList());
    }

    public Optional<String> getRoomByPatientAndDate(Integer patientId, LocalDateTime date) {
        return appointmentRepository.findByPatientAndStarto(patientId, date)
                .map(Appointment::getExaminationRoom);
    }

    public List<String> getRoomsByPhysicianAndDate(Integer physicianId, LocalDateTime date) {
        return appointmentRepository.findByPhysicianAndStarto(physicianId, date).stream()
                .map(Appointment::getExaminationRoom).collect(Collectors.toList());
    }

    public List<String> getRoomsByNurseAndDate(Integer nurseId, LocalDateTime date) {
        return appointmentRepository.findByPrepNurseAndStarto(nurseId, date).stream()
                .map(Appointment::getExaminationRoom).collect(Collectors.toList());
    }
}
