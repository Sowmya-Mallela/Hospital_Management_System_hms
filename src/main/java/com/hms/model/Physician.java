package com.hms.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "physician")
public class Physician {

    @Id
    @Column(name = "EmployeeID")
    private Integer employeeId;

    @Column(name = "name")
    private String name;

    @Column(name = "position")
    private String position;

    @Column(name = "SSN")
    private Integer ssn;

    public Physician() {}

    public Physician(Integer employeeId, String name, String position, Integer ssn) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.ssn = ssn;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getSsn() {
        return ssn;
    }

    public void setSsn(Integer ssn) {
        this.ssn = ssn;
    }
}