package com.digitalfactory.vaccnpw.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReportingRequest {

    private String periodFrom;
    private String periodTo;

    public ReportingRequest(String periodFrom, String periodTo) {
        this.periodFrom = periodFrom;
        this.periodTo = periodTo;
    }
}
