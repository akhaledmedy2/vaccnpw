package com.digitalfactory.vaccnpw.dto;

import com.digitalfactory.vaccnpw.entity.Branch;
import com.digitalfactory.vaccnpw.entity.Vaccine;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
public class ScheduleVaccinationRepresentation {

    private String vaccinatorName;
    private String vaccinatorEmail;
    private String nationalId;
    private Date scheduleTime;
    private Date timeFrom;
    private Date timeTo;
    private String paymentMethod;
    private String branch;
    private String vaccine;

}
