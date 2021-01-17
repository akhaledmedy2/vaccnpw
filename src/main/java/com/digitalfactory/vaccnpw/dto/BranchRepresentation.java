package com.digitalfactory.vaccnpw.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class BranchRepresentation {

    private long branchId;

    private String branchName;

    private Date timeStart;

    private Date timeEnd;

    private List<VaccineRepresentation> vaccinesPerBranch;
}
