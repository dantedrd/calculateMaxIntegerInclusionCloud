package com.inclusioncloud.maxinteger.domain.service;


import com.inclusioncloud.maxinteger.config.exception.CustomException;
import com.inclusioncloud.maxinteger.config.exception.SPError;
import com.inclusioncloud.maxinteger.domain.models.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@code OperationService} class, part of the domain service layer, encapsulates the business logic
 * for calculating the maximum non-negative integer k, such that 0 ≤ k ≤ n and k mod x = y.
 * It implements the core algorithm for the problem defined at https://codeforces.com/problemset/problem/1374/A.
 */
public class OperationService {

    private static final Logger logger = LoggerFactory.getLogger(OperationService.class);

    /**
     * Calculates the maximum non-negative integer k satisfying 0 ≤ k ≤ n and k mod x = y.
     * It uses the {@link Operation} object to retrieve the parameters x, y, and n,
     * and applies the algorithm to find the suitable value of k.
     *
     * @param domain An instance of {@link Operation} containing parameters x, y, and n.
     * @return The calculated maximum value of k that meets the conditions.
     * @throws CustomException If an error occurs during the calculation process.
     */
    public Long calculateMaxK(Operation domain) {
        if (domain.getX() < 0 || domain.getY() < 0 || domain.getN() < 0) {
            logger.error("Invalid input for MaxK calculation: x={}, y={}, n={}", domain.getX(), domain.getY(), domain.getN());
            throw new CustomException(SPError.OPERATION_SERVICE_ERROR_CALCULATE.getErrorCode(), SPError.OPERATION_SERVICE_ERROR_CALCULATE.getErrorMessage());
        }

        try {
            Long maxK = domain.getN() - (domain.getN() % domain.getX()) + domain.getY();
            if (maxK > domain.getN()) {
                maxK -= domain.getX();
            }
            logger.info("Calculated MaxK: {}", maxK);
            return maxK;
        } catch (Exception e) {
            logger.error("Error in MaxK calculation", e);
            throw new CustomException(SPError.OPERATION_SERVICE_ERROR_CALCULATE.getErrorCode(), SPError.OPERATION_SERVICE_ERROR_CALCULATE.getErrorMessage(), e);
        }
    }
}
