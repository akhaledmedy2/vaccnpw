package com.digitalfactory.vaccnpw.service;

import com.digitalfactory.vaccnpw.dao.BranchDao;
import com.digitalfactory.vaccnpw.dao.VaccineDao;
import com.digitalfactory.vaccnpw.dto.BranchRepresentation;
import com.digitalfactory.vaccnpw.dto.VaccineRepresentation;
import com.digitalfactory.vaccnpw.entity.Branch;
import com.digitalfactory.vaccnpw.entity.Vaccine;
import com.digitalfactory.vaccnpw.exceptions.CommonException;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AvailabilityServices {

    @Autowired
    private BranchDao branchDao;

    @Autowired
    private VaccineDao vaccineDao;

    public List<BranchRepresentation> getAllBranches() {
        List<Branch> branchList;

        try {
            branchList = branchDao.findAll();
            Preconditions.checkNotNull(branchList);
        } catch (Exception e) {
            throw new CommonException("No branches are available");
        }

        if (branchList.isEmpty()) {
            throw new CommonException("No branches are available");
        }

        List<BranchRepresentation> branchRepresentations = new ArrayList<>();

        for (Branch category : branchList) {
            branchRepresentations
                    .add(category.getBranchRepresentation());
        }
        return branchRepresentations;
    }

    public List<VaccineRepresentation> getAvailableVaccinesPerBranch(){
        List<Vaccine> vaccineBranchList = null;
        try {
            vaccineBranchList = vaccineDao.findAllByBranchVaccineQuantityGreaterThanZero();
            Preconditions.checkNotNull(vaccineBranchList);
        } catch (Exception e) {
            throw new CommonException("No vaccines available for all branches");
        }

        List<VaccineRepresentation> vaccineRepresentations = new ArrayList<>();

        for(Vaccine vaccineBranch : vaccineBranchList){
            vaccineRepresentations.add(vaccineBranch.getVaccineRepresentation());
        }

        return vaccineRepresentations;
    }

    public List<VaccineRepresentation> getAvailableVaccinesByBranch(long branchId){

        validateInput(branchId);

        List<Vaccine> vaccineBranchList = null;
        try {
            vaccineBranchList = branchDao.findAllByAvailableVaccineByBranch(branchId);
            Preconditions.checkNotNull(vaccineBranchList);
        } catch (Exception e) {
            throw new CommonException("No available vaccine for this branch");
        }

        List<VaccineRepresentation> vaccineRepresentations = new ArrayList<>();

        for(Vaccine vaccineBranch : vaccineBranchList){
            vaccineRepresentations.add(vaccineBranch.getVaccineRepresentation());
        }

        return vaccineRepresentations;
    }

    public List<BranchRepresentation> getAvailableTimeForBranch(long branchId){

        List<Branch> branchList;

        try {
            branchList = branchDao.findAllByBranchId(branchId);
            Preconditions.checkNotNull(branchList);
        } catch (Exception e) {
            throw new CommonException("No branches are available");
        }

        if (branchList.isEmpty()) {
            throw new CommonException("No branches are available");
        }

        List<BranchRepresentation> branchRepresentations = new ArrayList<>();

        for (Branch category : branchList) {
            branchRepresentations
                    .add(category.getBranchRepresentation());
        }
        return branchRepresentations;
    }

    private void validateInput(long branchId) {
        try {
            Preconditions.checkNotNull(branchId);
        } catch (Exception e) {
            throw new CommonException("missing branchId");
        }
    }
}
