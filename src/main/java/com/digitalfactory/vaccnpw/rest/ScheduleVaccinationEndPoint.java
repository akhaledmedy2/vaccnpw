package com.digitalfactory.vaccnpw.rest;

import com.digitalfactory.vaccnpw.dto.ScheduleVaccinationRepresentation;
import com.digitalfactory.vaccnpw.dto.ScheduleVaccinationRequest;
import com.digitalfactory.vaccnpw.service.ScheduleVaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@Scope(value = "request")
public class ScheduleVaccinationEndPoint extends ServiceExceptionsHandling {

    @Autowired
    private ScheduleVaccinationService scheduleVaccinationService;


    @PostMapping(value = "/scheduleVaccination", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScheduleVaccinationRepresentation> scheduleVaccination(@RequestBody ScheduleVaccinationRequest scheduleVaccinationRequest) throws ParseException {
        return new ResponseEntity<>(scheduleVaccinationService.scheduleVaccination(scheduleVaccinationRequest), HttpStatus.OK);
    }

}
