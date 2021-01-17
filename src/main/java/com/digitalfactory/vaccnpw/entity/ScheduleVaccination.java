package com.digitalfactory.vaccnpw.entity;

import com.digitalfactory.vaccnpw.dto.ScheduleVaccinationRepresentation;
import com.digitalfactory.vaccnpw.lisnter.EntityListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
@Entity
@EntityListeners(value= EntityListener.class)
@Table(name = "SCHEDULE_VACCINATION")
public class ScheduleVaccination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCHEDULE_ID")
    private long scheduleId;

    @NotNull
    @Column(name = "VACCINATOR_NAME")
    private String vaccinatorName;

    @NotNull
    @Column(name = "VACCINATOR_EMAIL")
    private String vaccinatorEmail;

    @NotNull
    @Column(name = "NATIONAL_ID")
    private String nationalId;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SCHEDULE_TIME")
    private Date scheduleTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIME_FROM")
    private Date timeFrom;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIME_TO")
    private Date timeTo;

    @NotNull
    @Column(name = "PAYMENT_METHOD")
    private String paymentMethod;

    @NotNull
    @OneToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @NotNull
    @OneToOne
    @JoinColumn(name = "vaccine_id")
    private Vaccine vaccine;

    @NotNull
    @Column(name = "CONFIRMED")
    private String confirmed;

    public ScheduleVaccinationRepresentation getScheduleVaccinationRepresentation() {

        ScheduleVaccinationRepresentation scheduleVaccinationRepresentation = new ScheduleVaccinationRepresentation();

        scheduleVaccinationRepresentation.setVaccinatorEmail(this.vaccinatorEmail);
        scheduleVaccinationRepresentation.setVaccinatorName(this.vaccinatorName);
        scheduleVaccinationRepresentation.setBranch(this.branch.getBranchName());
        scheduleVaccinationRepresentation.setNationalId(this.nationalId);
        scheduleVaccinationRepresentation.setPaymentMethod(this.paymentMethod);
        scheduleVaccinationRepresentation.setScheduleTime(this.scheduleTime);
        scheduleVaccinationRepresentation.setTimeFrom(this.timeFrom);
        scheduleVaccinationRepresentation.setTimeTo(this.timeTo);
        scheduleVaccinationRepresentation.setVaccine(this.vaccine.getVaccineName());

        return scheduleVaccinationRepresentation;
    }

}
