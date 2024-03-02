package com.inclusioncloud.maxinteger.infrastructure.entrypoints.controller;


import com.inclusioncloud.maxinteger.application.usecase.CalculateUseCase;
import com.inclusioncloud.maxinteger.application.usecase.ResultsUseCase;
import com.inclusioncloud.maxinteger.domain.models.Operation;
import com.inclusioncloud.maxinteger.mocks.MockFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for {@link OperationsController} using MockMvc and Mockito.
 * These tests ensure the controller responds correctly to various API requests.
 */
@ExtendWith(MockitoExtension.class)
class OperationsControllerTest {

    private static final String URL = "/api/v1/operation";

    private MockMvc mockMvc;

    @Mock
    private CalculateUseCase calculatorPort;

    @Mock
    private ResultsUseCase resultsPort;

    @InjectMocks
    private OperationsController operationsController;

    /**
     * Setup for MockMvc with injected mocks before each test.
     */
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(operationsController).build();
    }

    /**
     * Test the POST endpoint for a successful calculation.
     */
    @Test
    void calculate_Success() throws Exception {
        Operation mockResponse = MockFactory.getOperationDomainFactory();
        when(calculatorPort.calculate(any())).thenReturn(mockResponse);

        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MockFactory.getJsonContentValid()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data").exists());
    }

    /**
     * Test the POST endpoint with an invalid request.
     */
    @Test
    void calculate_InvalidRequest() throws Exception {
        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MockFactory.getJsonContentInvalid()))
                .andExpect(status().isBadRequest());
    }

    /**
     * Test the POST endpoint handling a calculation error.
     */
    @Test
    void calculate_CalculationError() throws Exception {
        when(calculatorPort.calculate(any())).thenThrow(MockFactory.getCustomExceptionUseCaseFactory());

        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MockFactory.getJsonContentValid()))
                .andExpect(status().isBadRequest());
    }

    /**
     * Test the GET endpoint for successfully retrieving results.
     */
    @Test
    void result_Success() throws Exception {
        when(resultsPort.getResults()).thenReturn(List.of(MockFactory.getOperationDomainFactory()));

        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }

    /**
     * Test the GET endpoint handling a service error.
     */
    @Test
    void result_ServiceError() throws Exception {
        when(resultsPort.getResults()).thenThrow(MockFactory.getCustomExceptionUseCaseFactory());

        mockMvc.perform(get(URL))
                .andExpect(status().isBadRequest());
    }

}
