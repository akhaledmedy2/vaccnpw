package com.digitalfactory.vaccnpw.dao;

import com.digitalfactory.vaccnpw.entity.ScheduleVaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ScheduleVaccinationDao extends JpaRepository<ScheduleVaccination,Long> {

    List<ScheduleVaccination> findAllByScheduleTimeOrderByScheduleTimeDesc(Date scheduleTime);

    List<ScheduleVaccination> findByScheduleTimeBetween(Date periodFrom,Date periodTo);
    List<ScheduleVaccination> findByConfirmedAndScheduleTimeBetween(String confirmed,Date periodFrom,Date periodTo);

}
