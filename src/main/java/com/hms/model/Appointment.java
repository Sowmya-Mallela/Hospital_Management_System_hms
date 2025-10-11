package com.hms.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AppointmentID")
    private Integer appointmentID;

    @Column(name = "Patient", nullable = false)
    private Integer patient;

    @Column(name = "PrepNurse")
    private Integer prepNurse;

    @Column(name = "Physician", nullable = false)
    private Integer physician;

    @Column(name = "Starto", nullable = false)
    private LocalDateTime starto;

    @Column(name = "Endo", nullable = false)
    private LocalDateTime endo;

    @Column(name = "ExaminationRoom", nullable = false)
    private String examinationRoom;

    public Appointment() {}

    public Appointment(Integer patient, Integer prepNurse, Integer physician,
                       LocalDateTime starto, LocalDateTime endo, String examinationRoom) {
        this.patient = patient;
        this.prepNurse = prepNurse;
        this.physician = physician;
        this.starto = starto;
        this.endo = endo;
        this.examinationRoom = examinationRoom;
    }

    public Integer getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(Integer appointmentID) {
        this.appointmentID = appointmentID;
    }

    public Integer getPatient() {
        return patient;
    }

    public void setPatient(Integer patient) {
        this.patient = patient;
    }

    public Integer getPrepNurse() {
        return prepNurse;
    }

    public void setPrepNurse(Integer prepNurse) {
        this.prepNurse = prepNurse;
    }

    public Integer getPhysician() {
        return physician;
    }

    public void setPhysician(Integer physician) {
        this.physician = physician;
    }

    public LocalDateTime getStarto() {
        return starto;
    }

    public void setStarto(LocalDateTime starto) {
        this.starto = starto;
    }

    public LocalDateTime getEndo() {
        return endo;
    }

    public void setEndo(LocalDateTime endo) {
        this.endo = endo;
    }

    public String getExaminationRoom() {
        return examinationRoom;
    }

    public void setExaminationRoom(String examinationRoom) {
        this.examinationRoom = examinationRoom;
    }
}