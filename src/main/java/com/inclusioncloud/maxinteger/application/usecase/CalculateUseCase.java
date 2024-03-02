package com.inclusioncloud.maxinteger.application.usecase;


import com.inclusioncloud.maxinteger.application.port.out.OperationStorageRepository;
import com.inclusioncloud.maxinteger.config.exception.CustomException;
import com.inclusioncloud.maxinteger.config.exception.SPError;
import com.inclusioncloud.maxinteger.domain.models.Operation;
import com.inclusioncloud.maxinteger.domain.service.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * to handle the calculation of the maximum non-negative integer k, based on the constraints
 * provided in the {@link Operation}. This class acts as an application service,
 * orchestrating the flow of data between the domain layer and the port interfaces. It
 * utilizes the {@link OperationService} for the core calculation logic and
 * {@link OperationStorageRepository} for persisting the results.
 */
public class CalculateUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CalculateUseCase.class);
    private final OperationService service;
    private final OperationStorageRepository repository;

    /**
     * Constructs a new instance of {@code CalculateUseCase} with the given domain service
     * and storage repository.
     *
     * @param service    The domain service responsible for performing the calculation logic.
     * @param repository The storage repository used for persisting operation results.
     */
    public CalculateUseCase(OperationService service, OperationStorageRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    /**
     * Performs the calculation of the maximum non-negative integer k based on the input
     * parameters x, y, and n provided in the {@link Operation}. This method also
     * persists the result and returns the updated domain entity.
     *
     * @param domain An instance of {@link Operation} containing the input parameters.
     * @return The updated {@link Operation} instance with the calculation result.
     * @throws CustomException If an error occurs during the calculation or data persistence.
     */
    public Operation calculate(Operation domain) {
        try {
            logger.info("Calculating in CalculateUseCase with the following data: {}", domain);
            Long result = service.calculateMaxK(domain);
            domain.setResult(result);
            Operation savedDomain = repository.save(domain);
            return Operation.builder()
                    .result(savedDomain.getResult())
                    .build();
        } catch (Exception e) {
            logger.error("Error in CalculateUseCase during calculation", e);
            throw new CustomException(SPError.OPERATION_USE_CASE_ERROR_CALCULATE.getErrorCode(), SPError.OPERATION_USE_CASE_ERROR_CALCULATE.getErrorMessage(), e.getCause());
        }
    }
}
