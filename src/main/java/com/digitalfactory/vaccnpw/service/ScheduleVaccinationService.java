package com.digitalfactory.vaccnpw.service;

import com.digitalfactory.vaccnpw.dao.BranchDao;
import com.digitalfactory.vaccnpw.dao.ScheduleVaccinationDao;
import com.digitalfactory.vaccnpw.dao.VaccineDao;
import com.digitalfactory.vaccnpw.dto.PaymentMethod;
import com.digitalfactory.vaccnpw.dto.ScheduleVaccinationRepresentation;
import com.digitalfactory.vaccnpw.dto.ScheduleVaccinationRequest;
import com.digitalfactory.vaccnpw.entity.Branch;
import com.digitalfactory.vaccnpw.entity.ScheduleVaccination;
import com.digitalfactory.vaccnpw.entity.Vaccine;
import com.digitalfactory.vaccnpw.exceptions.MissingOrBadParameterException;
import com.digitalfactory.vaccnpw.exceptions.NoAvailableTimeReservationException;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ScheduleVaccinationService {

    @Value("${schedule.vaccine.timeslot}")
    private long scheduleTimeslot;

    @Value("${schedule.vaccine.max}")
    private int maxVaccinationAtTime;

    @Autowired
    private BranchDao branchDao;

    @Autowired
    private VaccineDao vaccineDao;

    @Autowired
    private ScheduleVaccinationDao scheduleVaccinationDao;

    public ScheduleVaccinationRepresentation scheduleVaccination(ScheduleVaccinationRequest scheduleVaccinationRequest) throws ParseException {

        validateInput(scheduleVaccinationRequest);

        Branch branch = getSelectedBranch(scheduleVaccinationRequest);

        Vaccine vaccine = getSelectedVaccine(scheduleVaccinationRequest);

        ScheduleVaccination scheduleVaccination = buildScheduleVaccinationModel(branch, vaccine, scheduleVaccinationRequest);

        scheduleVaccinationDao.save(scheduleVaccination);

        updateVaccineQuantityPerBranch(vaccine);

        return scheduleVaccination.getScheduleVaccinationRepresentation();
    }

    private void updateVaccineQuantityPerBranch(Vaccine vaccine) {

        vaccine.setQuantity(vaccine.getQuantity() - 1);

        vaccineDao.save(vaccine);
    }


    private ScheduleVaccination buildScheduleVaccinationModel(Branch branch, Vaccine vaccine, ScheduleVaccinationRequest scheduleVaccinationRequest) throws ParseException {

        ScheduleVaccination scheduleVaccination = new ScheduleVaccination();
        scheduleVaccination.setVaccinatorName(scheduleVaccinationRequest.getVaccinatorName());
        scheduleVaccination.setVaccinatorEmail(scheduleVaccinationRequest.getVaccinatorEmail());
        scheduleVaccination.setConfirmed("false");
        scheduleVaccination.setBranch(branch);
        scheduleVaccination.setVaccine(vaccine);
        scheduleVaccination.setNationalId(scheduleVaccinationRequest.getNationalId());
        scheduleVaccination.setPaymentMethod(PaymentMethod.getEnum(String.valueOf(scheduleVaccinationRequest.getPaymentMethod())).name());

        handleVaccinationScheduleTime(scheduleVaccinationRequest.getScheduleTime(), branch, scheduleVaccination);

        return scheduleVaccination;
    }

    private void handleVaccinationScheduleTime(String scheduleVaccinationTime, Branch branch, ScheduleVaccination scheduleVaccination) throws ParseException {

        Date scheduledTimeRequired = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(scheduleVaccinationTime);

        List<ScheduleVaccination> vaccinationTimeslots = scheduleVaccinationDao.findAllByScheduleTimeOrderByScheduleTimeDesc(scheduledTimeRequired);

        if (vaccinationTimeslots.size() > maxVaccinationAtTime) {
            throw new NoAvailableTimeReservationException("");
        }

        if (!vaccinationTimeslots.isEmpty()) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(vaccinationTimeslots.get(vaccinationTimeslots.size() - 1).getTimeTo());
            scheduledTimeRequired = new Date(calendar.getTime().getTime() + scheduleTimeslot);
        } else {
            scheduledTimeRequired = new Date(branch.getTimeStart().getTime() + scheduleTimeslot);
        }

        scheduleVaccination.setScheduleTime(scheduledTimeRequired);
        scheduleVaccination.setTimeFrom(scheduledTimeRequired);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(scheduledTimeRequired);

        scheduleVaccination.setTimeTo(new Date(calendar.getTime().getTime() + scheduleTimeslot));

    }

    private Vaccine getSelectedVaccine(ScheduleVaccinationRequest scheduleVaccinationRequest) {
        return vaccineDao.findOneByAvailableVaccineByVaccineId(scheduleVaccinationRequest.getVaccineId());
    }

    private Branch getSelectedBranch(ScheduleVaccinationRequest scheduleVaccinationRequest) {
        return branchDao.findOneByBranchId(scheduleVaccinationRequest.getBranchId());
    }

    private void validateInput(ScheduleVaccinationRequest scheduleVaccinationRequest) {

        try {
            Preconditions.checkNotNull(scheduleVaccinationRequest);
        } catch (Exception e) {
            throw new MissingOrBadParameterException("missing schedule request");
        }

        try {
            Preconditions.checkNotNull(scheduleVaccinationRequest.getBranchId());
        } catch (Exception e) {
            throw new MissingOrBadParameterException("missing branch id");
        }

        try {
            Preconditions.checkNotNull(scheduleVaccinationRequest.getVaccineId());
        } catch (Exception e) {
            throw new MissingOrBadParameterException("missing vaccine id");
        }

        try {
            Preconditions.checkNotNull(scheduleVaccinationRequest.getNationalId());
        } catch (Exception e) {
            throw new MissingOrBadParameterException("missing national id");
        }

        try {
            Preconditions.checkNotNull(scheduleVaccinationRequest.getScheduleTime());
        } catch (Exception e) {
            throw new MissingOrBadParameterException("missing schedule time");
        }

    }
}
