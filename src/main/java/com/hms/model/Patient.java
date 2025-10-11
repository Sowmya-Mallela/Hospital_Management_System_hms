package com.hms.model;


import jakarta.persistence.*;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @Column(name = "SSN")
    private Integer ssn;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "InsuranceID")
    private Integer insuranceId;

    @Column(name = "PCP")
    private Integer pcp;

    public Patient() {
    }

    public Patient(Integer ssn, String name, String address, String phone, Integer insuranceId, Integer pcp) {
        this.ssn = ssn;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.insuranceId = insuranceId;
        this.pcp = pcp;
    }

    public Integer getSsn() {
        return ssn;
    }

    public void setSsn(Integer ssn) {
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Integer insuranceId) {
        this.insuranceId = insuranceId;
    }

    public Integer getPcp() {
        return pcp;
    }

    public void setPcp(Integer pcp) {
        this.pcp = pcp;
    }
}

//package com.sprint.hospital_management.entity;
//
//import jakarta.persistence.*;
//
//
//@Entity
//@Table(name = "patient")
//public class Patient {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer ssn;
//	private String name;
//	private String address;
//	private String phone;
//	private Integer insuranceId;
//	
//	public Patient() {}
//	
//	public Patient(Integer ssn, String name, String address ,String phone , Integer insuranceId) {
//		this.ssn = ssn;
//		this.name = name;
//		this.address = address;
//		this.phone = phone;
//		this.insuranceId = insuranceId;
//	}
//	
//	public Integer getInsuranceId() {
//		return insuranceId;
//	}
//	public void setInsuranceId(Integer insuranceId) {
//		this.insuranceId = insuranceId;
//	}
//	public String getPhone() {
//		return phone;
//	}
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
//	public String getAddress() {
//		return address;
//	}
//	public void setAddress(String address) {
//		this.address = address;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public Integer getSsn() {
//		return ssn;
//	}
//	public void setSsn(Integer ssn) {
//		this.ssn = ssn;
//	}
//	
//	
//}
//
