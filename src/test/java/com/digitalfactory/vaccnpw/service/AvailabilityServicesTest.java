package com.digitalfactory.vaccnpw.service;

import com.digitalfactory.vaccnpw.dao.BranchDao;
import com.digitalfactory.vaccnpw.dto.BranchRepresentation;
import com.digitalfactory.vaccnpw.entity.Branch;
import com.digitalfactory.vaccnpw.entity.Vaccine;
import com.digitalfactory.vaccnpw.exceptions.CommonException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AvailabilityServicesTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private BranchDao branchDao;

    @InjectMocks
    private AvailabilityServices service;

    @Test
    public void getAllBranches_WhenGetAllBranchesSuccess_returnBranchRepresentation() {

        when(branchDao.findAll()).thenReturn(mockBranchList());

        List<BranchRepresentation> branchRepresentations = service.getAllBranches();

        assertThat(branchRepresentations).isNotNull();
        assertThat(!branchRepresentations.isEmpty());

    }

    private List<Branch> mockBranchList() {

        Branch branch = new Branch();
        branch.setTimeStart(new Date());
        branch.setTimeEnd(new Date());
        branch.setBranchId(1);
        branch.setBranchName("Maadi Degla");
        Vaccine vaccine = new Vaccine();
        vaccine.setBranch(branch);
        vaccine.setQuantity(10);
        vaccine.setVaccineId(11);
        vaccine.setVaccineName("Fiser");
        Set<Vaccine> vaccines = new HashSet<>();
        vaccines.add(vaccine);
        branch.setVaccines(vaccines);

        List<Branch> branchList = new ArrayList<>();
        branchList.add(branch);
        return branchList;
    }

    @Test
    public void getAllBranches_WhenGetAllBranchesFailed_returnBranchRepresentation() {

        when(branchDao.findAll()).thenReturn(null);

        this.thrown.expect(CommonException.class);

        service.getAllBranches();

    }
}
