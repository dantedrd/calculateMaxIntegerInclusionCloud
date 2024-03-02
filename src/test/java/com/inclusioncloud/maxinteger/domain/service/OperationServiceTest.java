package com.inclusioncloud.maxinteger.domain.service;

import com.inclusioncloud.maxinteger.config.exception.CustomException;
import com.inclusioncloud.maxinteger.domain.models.Operation;
import com.inclusioncloud.maxinteger.mocks.MockFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationServiceTest {
    private final OperationService operationService = new OperationService();

    /**
     * Test the calculateMaxK method for successful calculation.
     */
    @Test
    void calculateMaxK_Success() {
        Operation domain = MockFactory.getOperationDomainFactory();
        Long result = operationService.calculateMaxK(domain);

        assertEquals(15L, result, "The calculated maxK should be correct for the given input");
    }

    /**
     * Test the calculateMaxK method for handling border case inputs.
     */
    @Test
    void calculateMaxK_BorderCase() {
        Operation domain = MockFactory.getOperationDomainBorderFactory();
        Long result = operationService.calculateMaxK(domain);

        assertEquals(20L, result, "The calculated maxK should handle border cases correctly");
    }

    /**
     * Test the calculateMaxK method for handling invalid inputs.
     */
    @Test
    void calculateMaxK_InvalidInput() {
        Operation domain = MockFactory.getOperationDomainInvalidFactory();

        assertThrows(CustomException.class, () -> operationService.calculateMaxK(domain),
                "A CustomException should be thrown for invalid input");
    }
}