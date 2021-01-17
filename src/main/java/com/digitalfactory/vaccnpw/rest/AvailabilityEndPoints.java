package com.digitalfactory.vaccnpw.rest;

import com.digitalfactory.vaccnpw.dto.BranchRepresentation;
import com.digitalfactory.vaccnpw.dto.VaccineRepresentation;
import com.digitalfactory.vaccnpw.service.AvailabilityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Scope(value = "request")
public class AvailabilityEndPoints extends ServiceExceptionsHandling{

    @Autowired
    private AvailabilityServices availabilityServices;

    @GetMapping(value = "/branches", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BranchRepresentation>> getAllBranches() {
        return new ResponseEntity<>(availabilityServices.getAllBranches(), HttpStatus.OK);
    }

    @GetMapping(value = "/availableVaccinesPerBranch", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VaccineRepresentation>> getAvailableVaccinesPerBranch() {
        return new ResponseEntity<>(availabilityServices.getAvailableVaccinesPerBranch(), HttpStatus.OK);
    }

    @GetMapping(value = "/availableVaccinesByBranch/{branchId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VaccineRepresentation>> getAvailableVaccinesByBranch(@PathVariable long branchId) {
        return new ResponseEntity<>(availabilityServices.getAvailableVaccinesByBranch(branchId), HttpStatus.OK);
    }

    @GetMapping(value = "/availableTimeByBranch/{branchId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BranchRepresentation>> getAvailableTimeByBranch(@PathVariable long branchId) {
        return new ResponseEntity<>(availabilityServices.getAvailableTimeForBranch(branchId), HttpStatus.OK);
    }

}
