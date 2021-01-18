package com.digitalfactory.vaccnpw.rest;

import com.digitalfactory.vaccnpw.dto.BranchRepresentation;
import com.digitalfactory.vaccnpw.dto.ReportingRequest;
import com.digitalfactory.vaccnpw.dto.ScheduleVaccinationRepresentation;
import com.digitalfactory.vaccnpw.dto.ScheduleVaccinationRequest;
import com.digitalfactory.vaccnpw.service.AvailabilityServices;
import com.digitalfactory.vaccnpw.service.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@Scope(value = "request")
public class ReportingEndPoints extends ServiceExceptionsHandling {

    @Autowired
    private ReportsService reportsService;

    @GetMapping(value = "/appliedVaccinationsPerBranch", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ScheduleVaccinationRepresentation>> getAppliedVaccinationsPerBranch() {
        return new ResponseEntity<>(reportsService.getAppliedVaccinationPerBranch(), HttpStatus.OK);
    }

    @PostMapping(value = "/appliedVaccinationPerDayOrPeriod", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ScheduleVaccinationRepresentation>> getAppliedVaccinationPerDayOrPeriod(@RequestBody ReportingRequest reportingRequest) {
        return new ResponseEntity<>(reportsService.getAppliedVaccinationPerDayOrPeriod(reportingRequest), HttpStatus.OK);
    }

    @PostMapping(value = "/confirmedVaccinationByPeriod", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ScheduleVaccinationRepresentation>> getConfirmedVaccinationByPeriod(@RequestBody ReportingRequest reportingRequest) {
        return new ResponseEntity<>(reportsService.getConfirmedVaccinationByPeriod(reportingRequest), HttpStatus.OK);
    }
}
