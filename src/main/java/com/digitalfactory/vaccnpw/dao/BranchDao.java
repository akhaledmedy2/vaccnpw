package com.digitalfactory.vaccnpw.dao;

import com.digitalfactory.vaccnpw.entity.Branch;
import com.digitalfactory.vaccnpw.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BranchDao extends JpaRepository<Branch, Long> {

    Branch findOneByBranchId(long branchId);

    @Query("SELECT v FROM Vaccine v , Branch b where v.branch = b.branchId and b.branchId = :branchId and v.quantity > 0")
    List<Vaccine> findAllByAvailableVaccineByBranch(@Param("branchId") long branchId);

    List<Branch> findAllByBranchId(long branchId);

}
