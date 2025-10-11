package com.hms.controller;

import com.hms.model.Appointment;
import com.hms.model.Patient;
import com.hms.model.Physician;
import com.hms.model.Nurse;
import com.hms.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<String> addAppointment(@RequestBody Appointment appointment) {
        appointmentService.addAppointment(appointment);
        return ResponseEntity.ok("Record Created Successfully");
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/{startDate}")
    public ResponseEntity<List<Appointment>> getAppointmentsByStartDate(@PathVariable String startDate) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByStartDate(LocalDateTime.parse(startDate)));
    }

    @GetMapping("/patient/appointment/{appointmentId}")
    public ResponseEntity<Patient> getPatientByAppointmentId(@PathVariable Integer appointmentId) {
        return appointmentService.getPatientByAppointmentId(appointmentId)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/physician/appointment/{appointmentId}")
    public ResponseEntity<Physician> getPhysicianByAppointmentId(@PathVariable Integer appointmentId) {
        return appointmentService.getPhysicianByAppointmentId(appointmentId)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nurse/appointment/{appointmentId}")
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

    @GetMapping("/patient/physician/{physicianId}/date/{date}")
    public ResponseEntity<List<Integer>> getPatientsByPhysicianAndDate(@PathVariable Integer physicianId, @PathVariable String date) {
        return ResponseEntity.ok(appointmentService.getPatientsByPhysicianAndDate(physicianId, LocalDateTime.parse(date)));
    }

    @GetMapping("/patient/physician/{physicianId}/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getPatientByPhysician(@PathVariable Integer physicianId, @PathVariable Integer patientId) {
        return ResponseEntity.ok(appointmentService.getPatientByPhysician(physicianId, patientId));
    }

    @GetMapping("/patient/nurse/{nurseId}")
    public ResponseEntity<List<Integer>> getPatientsByNurse(@PathVariable Integer nurseId) {
        return ResponseEntity.ok(appointmentService.getPatientsByNurse(nurseId));
    }

    @GetMapping("/patient/nurse/{nurseId}/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getPatientByNurse(@PathVariable Integer nurseId, @PathVariable Integer patientId) {
        return ResponseEntity.ok(appointmentService.getPatientByNurse(nurseId, patientId));
    }

    @GetMapping("/patient/nurse/{nurseId}/date/{date}")
    public ResponseEntity<List<Integer>> getPatientsByNurseAndDate(@PathVariable Integer nurseId, @PathVariable String date) {
        return ResponseEntity.ok(appointmentService.getPatientsByNurseAndDate(nurseId, LocalDateTime.parse(date)));
    }

    @GetMapping("/room/patient/{patientId}/{date}")
    public ResponseEntity<List<String>> getRoomsByPatientAndDate(@PathVariable Integer patientId, @PathVariable String date) {
        return ResponseEntity.ok(appointmentService.getRoomsByPatientAndDate(patientId, LocalDateTime.parse(date)));
    }

    @GetMapping("/room/physician/{physicianId}/{date}")
    public ResponseEntity<List<String>> getRoomsByPhysicianAndDate(@PathVariable Integer physicianId, @PathVariable String date) {
        return ResponseEntity.ok(appointmentService.getRoomsByPhysicianAndDate(physicianId, LocalDateTime.parse(date)));
    }

    @GetMapping("/room/nurse/{nurseId}/{date}")
    public ResponseEntity<List<String>> getRoomsByNurseAndDate(@PathVariable Integer nurseId, @PathVariable String date) {
        return ResponseEntity.ok(appointmentService.getRoomsByNurseAndDate(nurseId, LocalDateTime.parse(date)));
    }

    @GetMapping("/examinationroom/{appointmentId}")
    public ResponseEntity<String> getExaminationRoomByAppointmentId(@PathVariable Integer appointmentId) {
        return appointmentService.getExaminationRoomByAppointmentId(appointmentId)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/physician/patient/{patientId}")
    public ResponseEntity<List<Physician>> getPhysiciansByPatient(@PathVariable Integer patientId) {
        return ResponseEntity.ok(appointmentService.getPhysiciansByPatient(patientId));
    }

    @GetMapping("/physician/patient/{patientId}/{date}")
    public ResponseEntity<List<Physician>> getPhysicianByPatientAndDate(@PathVariable Integer patientId, @PathVariable String date) {
        return ResponseEntity.ok(appointmentService.getPhysicianByPatientAndDate(patientId, LocalDateTime.parse(date)));
    }

    @GetMapping("/nurse/patient/{patientId}")
    public ResponseEntity<List<Nurse>> getNursesByPatient(@PathVariable Integer patientId) {
        return ResponseEntity.ok(appointmentService.getNursesByPatient(patientId));
    }

    @GetMapping("/nurse/patient/{patientId}/{date}")
    public ResponseEntity<List<Nurse>> getNurseByPatientAndDate(@PathVariable Integer patientId, @PathVariable String date) {
        return ResponseEntity.ok(appointmentService.getNurseByPatientAndDate(patientId, LocalDateTime.parse(date)));
    }
}



//package com.hms.controller;
//
//import com.hms.model.*;
//import com.hms.service.AppointmentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/appointment")
//public class AppointmentController {
//
//    @Autowired
//    private AppointmentService appointmentService;
//
//    @PostMapping
//    public ResponseEntity<String> addAppointment(@RequestBody Appointment appointment) {
//        appointmentService.addAppointment(appointment);
//        return ResponseEntity.ok("Record Created Successfully");
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Appointment>> getAllAppointments() {
//        return ResponseEntity.ok(appointmentService.getAllAppointments());
//    }
//
//    @GetMapping("/{startDate}")
//    public ResponseEntity<List<Appointment>> getAppointmentsByStartDate(@PathVariable String startDate) {
//        return ResponseEntity.ok(appointmentService.getAppointmentsByStartDate(LocalDateTime.parse(startDate)));
//    }
//
//    @GetMapping("/patient/appointment/{appointmentId}")
//    public ResponseEntity<Patient> getPatientByAppointmentId(@PathVariable Integer appointmentId) {
//        return appointmentService.getPatientByAppointmentId(appointmentId)
//                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/physician/appointment/{appointmentId}")
//    public ResponseEntity<Physician> getPhysicianByAppointmentId(@PathVariable Integer appointmentId) {
//        return appointmentService.getPhysicianByAppointmentId(appointmentId)
//                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/nurse/appointment/{appointmentId}")
//    public ResponseEntity<Nurse> getNurseByAppointmentId(@PathVariable Integer appointmentId) {
//        return appointmentService.getNurseByAppointmentId(appointmentId)
//                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }
//
//    @PutMapping("/room/{appointmentId}")
//    public ResponseEntity<Appointment> updateRoom(@PathVariable Integer appointmentId, @RequestBody Appointment request) {
//        Appointment updated = appointmentService.updateRoom(appointmentId, request.getExaminationRoom());
//        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
//    }
//
//    @GetMapping("/date/{patientId}")
//    public ResponseEntity<List<LocalDateTime>> getDatesByPatient(@PathVariable Integer patientId) {
//        return ResponseEntity.ok(appointmentService.getDatesByPatient(patientId));
//    }
//
//    @GetMapping("/patient/physician/{physicianId}")
//    public ResponseEntity<List<Integer>> getPatientsByPhysician(@PathVariable Integer physicianId) {
//        return ResponseEntity.ok(appointmentService.getPatientsByPhysician(physicianId));
//    }
//
//    @GetMapping("/patient/physician/{physicianId}/date/{date}")
//    public ResponseEntity<List<Integer>> getPatientsByPhysicianAndDate(@PathVariable Integer physicianId, @PathVariable String date) {
//        return ResponseEntity.ok(appointmentService.getPatientsByPhysicianAndDate(physicianId, LocalDateTime.parse(date)));
//    }
//
//    @GetMapping("/patient/physician/{physicianId}/patient/{patientId}")
//    public ResponseEntity<Appointment> getPatientByPhysician(@PathVariable Integer physicianId, @PathVariable Integer patientId) {
//        return appointmentService.getPatientByPhysician(physicianId, patientId)
//                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/patient/nurse/{nurseId}")
//    public ResponseEntity<List<Integer>> getPatientsByNurse(@PathVariable Integer nurseId) {
//        return ResponseEntity.ok(appointmentService.getPatientsByNurse(nurseId));
//    }
//
//    @GetMapping("/patient/nurse/{nurseId}/patient/{patientId}")
//    public ResponseEntity<Appointment> getPatientByNurse(@PathVariable Integer nurseId, @PathVariable Integer patientId) {
//        return appointmentService.getPatientByNurse(nurseId, patientId)
//                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/patient/nurse/{nurseId}/date/{date}")
//    public ResponseEntity<List<Integer>> getPatientsByNurseAndDate(@PathVariable Integer nurseId, @PathVariable String date) {
//        return ResponseEntity.ok(appointmentService.getPatientsByNurseAndDate(nurseId, LocalDateTime.parse(date)));
//    }
//
//    @GetMapping("/room/patient/{patientId}/{date}")
//    public ResponseEntity<List<String>> getRoomsByPatientAndDate(@PathVariable Integer patientId, @PathVariable String date) {
//        return ResponseEntity.ok(appointmentService.getRoomsByPatientAndDate(patientId, LocalDateTime.parse(date)));
//    }
//
//    @GetMapping("/room/physician/{physicianId}/{date}")
//    public ResponseEntity<List<String>> getRoomsByPhysicianAndDate(@PathVariable Integer physicianId, @PathVariable String date) {
//        return ResponseEntity.ok(appointmentService.getRoomsByPhysicianAndDate(physicianId, LocalDateTime.parse(date)));
//    }
//
//    @GetMapping("/room/nurse/{nurseId}/{date}")
//    public ResponseEntity<List<String>> getRoomsByNurseAndDate(@PathVariable Integer nurseId, @PathVariable String date) {
//        return ResponseEntity.ok(appointmentService.getRoomsByNurseAndDate(nurseId, LocalDateTime.parse(date)));
//    }
//
//    @GetMapping("/examinationroom/{appointmentId}")
//    public ResponseEntity<String> getExaminationRoomByAppointmentId(@PathVariable Integer appointmentId) {
//        return appointmentService.getExaminationRoomByAppointmentId(appointmentId)
//                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/physician/patient/{patientId}")
//    public ResponseEntity<List<Physician>> getPhysiciansByPatient(@PathVariable Integer patientId) {
//        return ResponseEntity.ok(appointmentService.getPhysiciansByPatient(patientId));
//    }
//
//    @GetMapping("/physician/patient/{patientId}/{date}")
//    public ResponseEntity<List<Physician>> getPhysicianByPatientAndDate(
//         @PathVariable Integer patientId,
//         @PathVariable String date) {
//     return ResponseEntity.ok(
//         appointmentService.getPhysicianByPatientAndDate(patientId, LocalDateTime.parse(date))
//     );
//    }
//
//    @GetMapping("/nurse/patient/{patientId}")
//    public ResponseEntity<List<Nurse>> getNursesByPatient(@PathVariable Integer patientId) {
//        return ResponseEntity.ok(appointmentService.getNursesByPatient(patientId));
//    }
//
//    @GetMapping("/nurse/patient/{patientId}/{date}")
//    public ResponseEntity<List<Nurse>> getNurseByPatientAndDate(
//         @PathVariable Integer patientId,
//         @PathVariable String date) {
//     return ResponseEntity.ok(
//         appointmentService.getNurseByPatientAndDate(patientId, LocalDateTime.parse(date))
//     );
//    }
//}






//package com.hms.controller;
//
//import com.hms.model.*;
//import com.hms.service.AppointmentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/appointment")
//public class AppointmentController {
//
//    @Autowired
//    private AppointmentService appointmentService;
//
//    // POST: Add appointment
//    @PostMapping
//    public ResponseEntity<String> addAppointment(@RequestBody Appointment appointment) {
//        appointmentService.addAppointment(appointment);
//        return ResponseEntity.ok("Record Created Successfully");
//    }
// // GET: All appointments
//    @GetMapping
//    public ResponseEntity<List<Appointment>> getAllAppointments() {
//        return ResponseEntity.ok(appointmentService.getAllAppointments());
//    }
//    // GET: Appointments by start date
//    @GetMapping("/{startDate}")
//    public ResponseEntity<List<Appointment>> getAppointmentsByStartDate(@PathVariable String startDate) {
//        return ResponseEntity.ok(appointmentService.getAppointmentsByStartDate(LocalDateTime.parse(startDate)));
//    }
//
//    // GET: Patient by appointment ID
//    @GetMapping("/patient/appointment/{appointmentId}")
//    public ResponseEntity<Patient> getPatientByAppointmentId(@PathVariable Integer appointmentId) {
//        return appointmentService.getPatientByAppointmentId(appointmentId)
//                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }
//
//    // GET: Physician by appointment ID
//    @GetMapping("/physician/appointment/{appointmentId}")
//    public ResponseEntity<Physician> getPhysicianByAppointmentId(@PathVariable Integer appointmentId) {
//        return appointmentService.getPhysicianByAppointmentId(appointmentId)
//                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }
//
//    // GET: Nurse by appointment ID
//    @GetMapping("/nurse/appointment/{appointmentId}")
//    public ResponseEntity<Nurse> getNurseByAppointmentId(@PathVariable Integer appointmentId) {
//        return appointmentService.getNurseByAppointmentId(appointmentId)
//                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }
//
//    // PUT: Update room by appointment ID
//    @PutMapping("/room/{appointmentId}")
//    public ResponseEntity<Appointment> updateRoom(@PathVariable Integer appointmentId, @RequestBody Appointment request) {
//        Appointment updated = appointmentService.updateRoom(appointmentId, request.getExaminationRoom());
//        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
//    }
//
//    // GET: Dates by patient ID
//    @GetMapping("/date/{patientId}")
//    public ResponseEntity<List<LocalDateTime>> getDatesByPatient(@PathVariable Integer patientId) {
//        return ResponseEntity.ok(appointmentService.getDatesByPatient(patientId));
//    }
//
//    // GET: Patients by physician ID
//    @GetMapping("/patient/physician/{physicianId}")
//    public ResponseEntity<List<Integer>> getPatientsByPhysician(@PathVariable Integer physicianId) {
//        return ResponseEntity.ok(appointmentService.getPatientsByPhysician(physicianId));
//    }
//
//    // GET: Patients by physician ID and date
//    @GetMapping("/patient/physician/{physicianId}/{date}")
//    public ResponseEntity<List<Integer>> getPatientsByPhysicianAndDate(@PathVariable Integer physicianId, @PathVariable String date) {
//        return ResponseEntity.ok(appointmentService.getPatientsByPhysicianAndDate(physicianId, LocalDateTime.parse(date)));
//    }
//
//    // GET: Patient by physician ID and patient ID
//    @GetMapping("/patient/physician/{physicianId}/{patientId}")
//    public ResponseEntity<Appointment> getPatientByPhysician(@PathVariable Integer physicianId, @PathVariable Integer patientId) {
//        return appointmentService.getPatientByPhysician(physicianId, patientId)
//                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }
//
//    // GET: Patients by nurse ID
//    @GetMapping("/patient/nurse/{nurseId}")
//    public ResponseEntity<List<Integer>> getPatientsByNurse(@PathVariable Integer nurseId) {
//        return ResponseEntity.ok(appointmentService.getPatientsByNurse(nurseId));
//    }
//
//    // GET: Patient by nurse ID and patient ID
//    @GetMapping("/patient/{nurseId}/{patientId}")
//    public ResponseEntity<Appointment> getPatientByNurse(@PathVariable Integer nurseId, @PathVariable Integer patientId) {
//        return appointmentService.getPatientByNurse(nurseId, patientId)
//                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }
//
//    // GET: Patients by nurse ID and date
//    @GetMapping("/patient/nurse/{nurseId}/{date}")
//    public ResponseEntity<List<Integer>> getPatientsByNurseAndDate(@PathVariable Integer nurseId, @PathVariable String date) {
//        return ResponseEntity.ok(appointmentService.getPatientsByNurseAndDate(nurseId, LocalDateTime.parse(date)));
//    }
//
//    // GET: Room(s) by patient ID and date
//    @GetMapping("/room/patient/{patientId}/{date}")
//    public ResponseEntity<List<String>> getRoomByPatientAndDate(@PathVariable Integer patientId, @PathVariable String date) {
//        return ResponseEntity.ok(appointmentService.getRoomsByPatientAndDate(patientId, LocalDateTime.parse(date)));
//    }
//
//    // GET: Rooms by physician ID and date
//    @GetMapping("/room/physician/{physicianId}/{date}")
//    public ResponseEntity<List<String>> getRoomsByPhysicianAndDate(@PathVariable Integer physicianId, @PathVariable String date) {
//        return ResponseEntity.ok(appointmentService.getRoomsByPhysicianAndDate(physicianId, LocalDateTime.parse(date)));
//    }
//
//    // GET: Rooms by nurse ID and date
//    @GetMapping("/room/nurse/{nurseId}/{date}")
//    public ResponseEntity<List<String>> getRoomsByNurseAndDate(@PathVariable Integer nurseId, @PathVariable String date) {
//        return ResponseEntity.ok(appointmentService.getRoomsByNurseAndDate(nurseId, LocalDateTime.parse(date)));
//    }
//
//    // GET: Examination room by appointment ID
//    @GetMapping("/examinationroom/{appointmentId}")
//    public ResponseEntity<String> getExaminationRoomByAppointmentId(@PathVariable Integer appointmentId) {
//        return appointmentService.getExaminationRoomByAppointmentId(appointmentId)
//                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }
//
//    // GET: Physicians by patient ID
//    @GetMapping("/physician/patient/{patientId}")
//    public ResponseEntity<List<Physician>> getPhysiciansByPatient(@PathVariable Integer patientId) {
//        return ResponseEntity.ok(appointmentService.getPhysiciansByPatient(patientId));
//    }
//
//    // GET: Physician by patient ID and date
//    @GetMapping("/physician/patient/{patientId}/{date}")
//    public ResponseEntity<Physician> getPhysicianByPatientAndDate(@PathVariable Integer patientId, @PathVariable String date) {
//        return appointmentService.getPhysicianByPatientAndDate(patientId, LocalDateTime.parse(date))
//                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }
//
//    // GET: Nurses by patient ID
//    @GetMapping("/nurse/patient/{patientId}")
//    public ResponseEntity<List<Nurse>> getNursesByPatient(@PathVariable Integer patientId) {
//        return ResponseEntity.ok(appointmentService.getNursesByPatient(patientId));
//    }
//
//    // GET: Nurse by patient ID and date
//    @GetMapping("/nurse/patient/{patientId}/{date}")
//    public ResponseEntity<Nurse> getNurseByPatientAndDate(@PathVariable Integer patientId, @PathVariable String date) {
//        return appointmentService.getNurseByPatientAndDate(patientId, LocalDateTime.parse(date))
//                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }
//}