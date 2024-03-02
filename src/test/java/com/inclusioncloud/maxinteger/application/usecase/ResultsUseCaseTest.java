package com.inclusioncloud.maxinteger.application.usecase;


import com.inclusioncloud.maxinteger.application.port.out.OperationStorageRepository;
import com.inclusioncloud.maxinteger.config.exception.CustomException;
import com.inclusioncloud.maxinteger.config.exception.SPError;
import com.inclusioncloud.maxinteger.domain.models.Operation;
import com.inclusioncloud.maxinteger.mocks.MockFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link ResultsUseCase}.
 * These tests validate the functionality of retrieving operation results under various scenarios.
 */
@ExtendWith(MockitoExtension.class)
class ResultsUseCaseTest {

    @Mock
    private OperationStorageRepository repository;

    @InjectMocks
    private ResultsUseCase resultsUseCase;

    @BeforeEach
    void setUp() {
        // Setup method is optional here as no additional setup is required before each test.
    }

    /**
     * Test to ensure successful retrieval of operation results.
     */
    @Test
    void getResults_Success() {
        Operation mockDomain = MockFactory.getOperationDomainFactory();
        List<Operation> expectedResults = List.of(mockDomain);
        when(repository.getAll()).thenReturn(expectedResults);

        List<Operation> actualResults = resultsUseCase.getResults();

        assertNotNull(actualResults, "The results should not be null");
        assertEquals(expectedResults.size(), actualResults.size(), "The size of the results should match the expected value");
        assertEquals(mockDomain, actualResults.get(0), "The contents of the result should match the expected operation domain");
    }

    /**
     * Test to verify behavior when no results are available.
     */
    @Test
    void getResults_EmptyResults() {
        when(repository.getAll()).thenReturn(Collections.emptyList());

        List<Operation> actualResults = resultsUseCase.getResults();

        assertNotNull(actualResults, "The results should not be null");
        assertTrue(actualResults.isEmpty(), "The results should be empty");
    }

    /**
     * Test to verify behavior when an error occurs during result retrieval.
     */
    @Test
    void getResults_Error() {
        when(repository.getAll()).thenThrow(MockFactory.getCustomExceptionUseCaseFactory());

        CustomException thrown = assertThrows(CustomException.class, () -> resultsUseCase.getResults());

        assertEquals(SPError.OPERATION_USE_CASE_ERROR_RESULTS.getErrorCode(), thrown.getErrorCode(), "The error code should match the expected value");
        assertEquals(SPError.OPERATION_USE_CASE_ERROR_RESULTS.getErrorMessage(), thrown.getMessage(), "The error message should match the expected value");
    }
}
