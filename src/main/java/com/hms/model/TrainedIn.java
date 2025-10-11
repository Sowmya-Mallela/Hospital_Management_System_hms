package com.hms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trained_in")
public class TrainedIn {

    @EmbeddedId
    private TrainedInId id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "CertificationDate")
    private LocalDateTime certificationDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "CertificationExpires")
    private LocalDateTime certificationExpires;

    public TrainedIn() {}

    public TrainedInId getId() {
        return id;
    }

    public void setId(TrainedInId id) {
        this.id = id;
    }

    public LocalDateTime getCertificationDate() {
        return certificationDate;
    }

    public void setCertificationDate(LocalDateTime certificationDate) {
        this.certificationDate = certificationDate;
    }

    public LocalDateTime getCertificationExpires() {
        return certificationExpires;
    }

    public void setCertificationExpires(LocalDateTime certificationExpires) {
        this.certificationExpires = certificationExpires;
    }
}