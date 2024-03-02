package com.inclusioncloud.maxinteger.application.usecase;


import com.inclusioncloud.maxinteger.application.port.out.OperationStorageRepository;
import com.inclusioncloud.maxinteger.config.exception.CustomException;
import com.inclusioncloud.maxinteger.config.exception.SPError;
import com.inclusioncloud.maxinteger.domain.models.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * to handle the use case of retrieving results of all performed operations. This class
 * acts as an application service, facilitating the interaction between the application
 * layer and the domain layer. It utilizes {@link OperationStorageRepository} for accessing
 * persisted operation results.
 */
public class ResultsUseCase{

    private static final Logger logger = LoggerFactory.getLogger(ResultsUseCase.class);
    private final OperationStorageRepository repository;

    /**
     * Constructs a new instance of {@code ResultsUseCase}. This constructor initializes
     * the repository responsible for data retrieval.
     *
     * @param repository The {@link OperationStorageRepository} used to retrieve operation results.
     */
    public ResultsUseCase(OperationStorageRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves a list of all {@link Operation} results that have been persisted.
     * It fetches the historical data of operation calculations and is used to provide
     * insight into past computations.
     *
     * @return A {@code List} of {@link Operation} instances, each representing a stored operation result.
     * @throws CustomException If an error occurs during the data retrieval process.
     */
    public List<Operation> getResults() {
        try {
            logger.info("Fetching all operation results");
            return this.repository.getAll();
        } catch (Exception e) {
            logger.error("Error fetching operation results", e);
            throw new CustomException(SPError.OPERATION_USE_CASE_ERROR_RESULTS.getErrorCode(), SPError.OPERATION_USE_CASE_ERROR_RESULTS.getErrorMessage(), e.getCause());
        }
    }
}
