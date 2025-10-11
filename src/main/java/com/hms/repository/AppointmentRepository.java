//package com.hms.repository;
//
//import com.hms.model.Appointment;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
//    List<Appointment> findByStarto(LocalDateTime starto);
//    List<Appointment> findByPatient(Integer patient);
//    List<Appointment> findByPhysician(Integer physician);
//    List<Appointment> findAllByPatientAndStarto(Integer patient, LocalDateTime starto);
//    List<Appointment> findByPhysicianAndStarto(Integer physician, LocalDateTime starto);
//    Optional<Appointment> findByPhysicianAndPatient(Integer physician, Integer patient);
//    List<Appointment> findByPrepNurse(Integer prepNurse);
//    Optional<Appointment> findByPrepNurseAndPatient(Integer prepNurse, Integer patient);
//    List<Appointment> findByPrepNurseAndStarto(Integer prepNurse, LocalDateTime starto);
//    //Optional<Appointment> findByPatientAndStarto(Integer patient, LocalDateTime starto);
//    List<Appointment> findByPatientAndStarto(Integer patient, LocalDateTime starto);
//}
//
package com.hms.repository;

import com.hms.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByStarto(LocalDateTime starto);
    List<Appointment> findByPatient(Integer patient);
    List<Appointment> findByPhysician(Integer physician);
    List<Appointment> findByPhysicianAndStarto(Integer physician, LocalDateTime starto);
    List<Appointment> findByPhysicianAndPatient(Integer physician, Integer patient);
    List<Appointment> findByPrepNurse(Integer prepNurse);
    List<Appointment> findByPrepNurseAndPatient(Integer prepNurse, Integer patient);
    List<Appointment> findByPrepNurseAndStarto(Integer prepNurse, LocalDateTime starto);
    List<Appointment> findByPatientAndStarto(Integer patient, LocalDateTime starto);
}