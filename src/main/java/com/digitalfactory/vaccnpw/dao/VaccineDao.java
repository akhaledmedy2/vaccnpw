package com.digitalfactory.vaccnpw.dao;

import com.digitalfactory.vaccnpw.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VaccineDao extends JpaRepository<Vaccine, Long> {

    Vaccine findOneByVaccineId(long vaccineId);

    @Query("SELECT v FROM Vaccine v , Branch b where v.branch = b.branchId and v.vaccineId = :vaccineId and v.quantity > 0")
    Vaccine findOneByAvailableVaccineByVaccineId(@Param("vaccineId") long vaccineId);

    @Query("SELECT v FROM Vaccine v , Branch b where v.branch = b.branchId and v.quantity > 0")
    List<Vaccine> findAllByBranchVaccineQuantityGreaterThanZero();


}
