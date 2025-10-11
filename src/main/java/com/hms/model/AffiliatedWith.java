package com.hms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "affiliated_with")
public class AffiliatedWith {

    @EmbeddedId
    private AffiliatedWithId id;

    @Column(name = "PrimaryAffiliation")
    private Boolean primaryAffiliation;

    public AffiliatedWith() {}

    public AffiliatedWith(AffiliatedWithId id, Boolean primaryAffiliation) {
        this.id = id;
        this.primaryAffiliation = primaryAffiliation;
    }

    public AffiliatedWithId getId() {
        return id;
    }

    public void setId(AffiliatedWithId id) {
        this.id = id;
    }

    public Boolean getPrimaryAffiliation() {
        return primaryAffiliation;
    }

    public void setPrimaryAffiliation(Boolean primaryAffiliation) {
        this.primaryAffiliation = primaryAffiliation;
    }
}