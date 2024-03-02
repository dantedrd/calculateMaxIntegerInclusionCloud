package com.inclusioncloud.maxinteger.application.usecase;


import com.inclusioncloud.maxinteger.application.port.out.OperationStorageRepository;
import com.inclusioncloud.maxinteger.config.exception.CustomException;
import com.inclusioncloud.maxinteger.config.exception.SPError;
import com.inclusioncloud.maxinteger.domain.models.Operation;
import com.inclusioncloud.maxinteger.domain.service.OperationService;
import com.inclusioncloud.maxinteger.mocks.MockFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link CalculateUseCase} class.
 * These tests ensure the correctness of the calculate use case logic under various scenarios.
 */
@ExtendWith(MockitoExtension.class)
class CalculateUseCaseTest {

    @Mock
    private OperationService operationService;

    @Mock
    private OperationStorageRepository repository;

    @InjectMocks
    private CalculateUseCase calculateUseCase;

    private Operation inputDomain;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        inputDomain = MockFactory.getOperationDomainFactory();
    }

    /**
     * Test to ensure successful execution of the calculate method in {@link CalculateUseCase}.
     */
    @Test
    void calculate_Success() {
        when(operationService.calculateMaxK(inputDomain)).thenReturn(123L);
        when(repository.save(inputDomain)).thenReturn(inputDomain);

        Operation result = calculateUseCase.calculate(inputDomain);

        assertNotNull(result, "The result should not be null");
        assertEquals(123L, result.getResult(), "The result should match the expected value");
        verify(repository, times(1)).save(inputDomain);
    }

    /**
     * Test to verify behavior when a calculation error occurs in {@link CalculateUseCase}.
     */
    @Test
    void calculate_CalculationError() {
        when(operationService.calculateMaxK(inputDomain)).thenThrow(MockFactory.getCustomExceptionUseCaseFactory());

        CustomException thrown = assertThrows(CustomException.class, () -> calculateUseCase.calculate(inputDomain));

        assertEquals(SPError.OPERATION_USE_CASE_ERROR_CALCULATE.getErrorCode(), thrown.getErrorCode(), "The error code should match the expected value");
        assertEquals(SPError.OPERATION_USE_CASE_ERROR_CALCULATE.getErrorMessage(), thrown.getMessage(), "The error message should match the expected value");
    }

    /**
     * Test to verify behavior when a persistence error occurs in {@link CalculateUseCase}.
     */
    @Test
    void calculate_PersistenceError() {
        when(operationService.calculateMaxK(inputDomain)).thenReturn(123L);
        when(repository.save(inputDomain)).thenThrow(MockFactory.getCustomExceptionUseCaseFactory());

        CustomException thrown = assertThrows(CustomException.class, () -> calculateUseCase.calculate(inputDomain));

        assertEquals(SPError.OPERATION_USE_CASE_ERROR_CALCULATE.getErrorCode(), thrown.getErrorCode(), "The error code should match the expected value");
        assertEquals(SPError.OPERATION_USE_CASE_ERROR_CALCULATE.getErrorMessage(), thrown.getMessage(), "The error message should match the expected value");
    }
}
