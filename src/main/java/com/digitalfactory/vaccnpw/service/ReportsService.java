package com.digitalfactory.vaccnpw.service;

import com.digitalfactory.vaccnpw.dao.ScheduleVaccinationDao;
import com.digitalfactory.vaccnpw.dto.ReportingRequest;
import com.digitalfactory.vaccnpw.dto.ScheduleVaccinationRepresentation;
import com.digitalfactory.vaccnpw.entity.ScheduleVaccination;
import com.digitalfactory.vaccnpw.exceptions.CommonException;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportsService {

    @Autowired
    private ScheduleVaccinationDao scheduleVaccinationDao;

    public List<ScheduleVaccinationRepresentation> getAppliedVaccinationPerBranch() {

        List<ScheduleVaccination> scheduleVaccinationList;

        try {
            scheduleVaccinationList = scheduleVaccinationDao.findAll();
            Preconditions.checkNotNull(scheduleVaccinationList);
        } catch (Exception e) {
            throw new CommonException("No Reports are available");
        }

        if (scheduleVaccinationList.isEmpty()) {
            throw new CommonException("No branches are available");
        }

        List<ScheduleVaccinationRepresentation> scheduleVaccinationRepresentations = new ArrayList<>();

        scheduleVaccinationList.forEach(item -> scheduleVaccinationRepresentations.add(item.getScheduleVaccinationRepresentation()));

        return scheduleVaccinationRepresentations;

    }

    public List<ScheduleVaccinationRepresentation> getAppliedVaccinationPerDayOrPeriod(ReportingRequest reportingRequest) {

        List<ScheduleVaccination> scheduleVaccinationList;

        try {
            scheduleVaccinationList = scheduleVaccinationDao.findByScheduleTimeBetween(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(reportingRequest.getPeriodFrom()), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(reportingRequest.getPeriodTo()));
            Preconditions.checkNotNull(scheduleVaccinationList);
        } catch (Exception e) {
            throw new CommonException("No Reports are available");
        }

        if (scheduleVaccinationList.isEmpty()) {
            throw new CommonException("No branches are available");
        }

        List<ScheduleVaccinationRepresentation> scheduleVaccinationRepresentations = new ArrayList<>();

        scheduleVaccinationList.forEach(item -> scheduleVaccinationRepresentations.add(item.getScheduleVaccinationRepresentation()));

        return scheduleVaccinationRepresentations;
    }

    public List<ScheduleVaccinationRepresentation> getConfirmedVaccinationByPeriod(ReportingRequest reportingRequest) {

        List<ScheduleVaccination> scheduleVaccinationList;

        try {
            scheduleVaccinationList = scheduleVaccinationDao.findByConfirmedAndScheduleTimeBetween("true",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(reportingRequest.getPeriodFrom()), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(reportingRequest.getPeriodTo()));
            Preconditions.checkNotNull(scheduleVaccinationList);
        } catch (Exception e) {
            throw new CommonException("No Reports are available");
        }

        if (scheduleVaccinationList.isEmpty()) {
            throw new CommonException("No branches are available");
        }

        List<ScheduleVaccinationRepresentation> scheduleVaccinationRepresentations = new ArrayList<>();

        scheduleVaccinationList.forEach(item -> scheduleVaccinationRepresentations.add(item.getScheduleVaccinationRepresentation()));

        return scheduleVaccinationRepresentations;
    }
}
