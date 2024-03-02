package com.inclusioncloud.maxinteger.application.port.out;

import com.inclusioncloud.maxinteger.domain.models.Operation;

import java.util.List;

/**
 * The {@code OperationStorageRepository} interface defines the outbound port for
 * persisting operation results. According to hexagonal architecture principles,
 * this port represents the secondary side which is to be adapted to external
 * persistence mechanisms (e.g., databases).
 *
 * Implementations of this port will interact with the data storage layer, ensuring
 * that operation results are stored and retrieved as needed.
 */
public interface OperationStorageRepository {

    /**
     * Persists an {@link Operation} object to the storage mechanism.
     * This method is responsible for saving the result of a calculation.
     *
     * @param domain The {@link Operation} instance to be saved.
     * @return The persisted {@link Operation} instance, typically with
     *         additional information provided by the storage layer, such as a unique identifier.
     */
    Operation save(Operation domain);

    /**
     * Retrieves all persisted {@link Operation} objects from the storage mechanism.
     * This method will fetch the complete set of operation results that have been stored.
     *
     * @return A {@code List} of {@link Operation} instances, representing
     *         all the operation results that are currently stored.
     */
    List<Operation> getAll();

}
