package com.digitalfactory.vaccnpw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ScheduleVaccinationRequest {

    private long branchId;
    private long vaccineId;
    private long paymentMethod;
    private String nationalId;
    private String scheduleTime;
    private String vaccinatorName;
    private String vaccinatorEmail;

}
