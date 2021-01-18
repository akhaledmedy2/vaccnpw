package com.digitalfactory.vaccnpw.rest;

import com.digitalfactory.vaccnpw.exceptions.CommonException;
import com.digitalfactory.vaccnpw.service.AvailabilityServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class AvailabilityEndPointsTests {

    private MockMvc mockMvc;

    @Mock
    private AvailabilityServices availabilityServices;

    @InjectMocks
    private AvailabilityEndPoints endPoint;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(endPoint).build();
    }

    @Test
    public void getBranches_WhenGetBranches_ReturnSuccessResponse() throws Exception {
        getBranchesSuccess_Mocks();

        mockMvc.perform(MockMvcRequestBuilders.get("/branches")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private void getBranchesSuccess_Mocks() {
        when(availabilityServices.getAllBranches())
                .thenReturn(new ArrayList<>());
    }

    @Test
    public void getBranches_WhenCommonExceptionThrown_WillReturnErrorCode502WithMessage502() throws Exception {
        getBranches_WhenCommonExceptionThrown_WillReturnErrorCode502WithMessage502_Mocks();

        mockMvc.perform(MockMvcRequestBuilders.get("/branches")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadGateway())
                .andExpect(status().reason("502"));
    }

    private void getBranches_WhenCommonExceptionThrown_WillReturnErrorCode502WithMessage502_Mocks() {
        when(availabilityServices.getAllBranches())
                .thenThrow(new CommonException("No branches are available"));
    }
}
