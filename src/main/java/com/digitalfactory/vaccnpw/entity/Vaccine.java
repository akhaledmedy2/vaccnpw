package com.digitalfactory.vaccnpw.entity;

import com.digitalfactory.vaccnpw.dto.VaccineRepresentation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
@Table(name = "VACCINE")
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VACCINE_ID")
    private long vaccineId;

    @NotNull
    @Column(name = "VACCINE_NAME")
    private String vaccineName;

    @NotNull
    @Column(name = "QUANTITY")
    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BRANCH_ID", nullable = false)
    private Branch branch;

    @OneToOne(mappedBy = "vaccine")
    private ScheduleVaccination scheduleVaccination;

    public VaccineRepresentation getVaccineRepresentation() {

        VaccineRepresentation vaccineRepresentation = new VaccineRepresentation();
        vaccineRepresentation.setBranchId(this.branch.getBranchId());
        vaccineRepresentation.setQuantity(this.quantity);
        vaccineRepresentation.setVaccineId(this.vaccineId);
        vaccineRepresentation.setVaccineName(this.vaccineName);

        return vaccineRepresentation;
    }
}
