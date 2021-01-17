package com.digitalfactory.vaccnpw.entity;

import com.digitalfactory.vaccnpw.dto.BranchRepresentation;
import com.digitalfactory.vaccnpw.dto.VaccineRepresentation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "BRANCH")
public class Branch {

    @Id
    @Column(name = "BRANCH_ID")
    private long branchId;

    @NotNull
    @Column(name = "BRANCH_NAME")
    private String branchName;

    @NotNull
    @Temporal(TemporalType.TIME)
    @Column(name = "TIME_START")
    private Date timeStart;

    @NotNull
    @Temporal(TemporalType.TIME)
    @Column(name = "TIME_END")
    private Date timeEnd;

    @OneToMany(mappedBy = "branch", fetch = FetchType.EAGER)
    private Set<Vaccine> vaccines;

    @OneToOne(mappedBy = "branch")
    private ScheduleVaccination scheduleVaccination;


    public BranchRepresentation getBranchRepresentation() {

        BranchRepresentation branchRepresentation = new BranchRepresentation();
        branchRepresentation.setBranchId(this.branchId);
        branchRepresentation.setBranchName(this.branchName);
        branchRepresentation.setTimeStart(this.timeStart);
        branchRepresentation.setTimeEnd(this.timeEnd);
        List<VaccineRepresentation> vaccinesBranchRepresentation = new ArrayList<>();

        for(Vaccine vaccineBranch : this.vaccines){
            vaccinesBranchRepresentation.add(vaccineBranch.getVaccineRepresentation());
        }
        branchRepresentation.setVaccinesPerBranch(vaccinesBranchRepresentation);

        return branchRepresentation;
    }
}
