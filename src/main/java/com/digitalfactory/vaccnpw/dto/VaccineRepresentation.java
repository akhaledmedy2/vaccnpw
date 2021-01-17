package com.digitalfactory.vaccnpw.dto;

import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Setter
@Getter
public class VaccineRepresentation {

    private long vaccineId;

    private String vaccineName;

    private int quantity;

    @JsonIgnore
    private long branchId;
}
