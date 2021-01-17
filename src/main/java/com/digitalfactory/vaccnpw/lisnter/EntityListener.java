package com.digitalfactory.vaccnpw.lisnter;

import com.digitalfactory.vaccnpw.dao.ScheduleVaccinationDao;
import com.digitalfactory.vaccnpw.entity.ScheduleVaccination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.persistence.PostPersist;

public class EntityListener {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ScheduleVaccinationDao scheduleVaccinationDao;

    @PostPersist
    public void onUpdateMethod(Object entity) {

        ScheduleVaccination scheduleVaccinationEntity = (ScheduleVaccination) entity;

        StringBuilder sb = new StringBuilder();
        sb.append("Dear Mr/Mrs ").append(scheduleVaccinationEntity.getVaccinatorName())
                .append("\n")
                .append("Your vaccination scheduled as the following :-").append("\n")
                .append("Reservation Code : ").append(scheduleVaccinationEntity.getScheduleId()).append("\n")
                .append("National Id : ").append(scheduleVaccinationEntity.getNationalId()).append("\n")
                .append("Reservation Time In : ").append(scheduleVaccinationEntity.getTimeFrom()).append("\n")
                .append("Reservation Time Out : ").append(scheduleVaccinationEntity.getTimeTo()).append("\n")
                .append("Payment Method : ").append(scheduleVaccinationEntity.getPaymentMethod()).append("\n")
                .append("Vaccination Name : ").append(scheduleVaccinationEntity.getVaccine().getVaccineName()).append("\n")
                .append("Vaccination Branch at : ").append(scheduleVaccinationEntity.getBranch().getBranchName());

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(scheduleVaccinationEntity.getVaccinatorEmail());
        msg.setSubject("Vaccination Scheduling Confirmation");
        msg.setText(sb.toString());

        javaMailSender.send(msg);

        scheduleVaccinationEntity.setConfirmed("true");

        scheduleVaccinationDao.save(scheduleVaccinationEntity);

    }
}
