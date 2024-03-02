package com.inclusioncloud.maxinteger.domain.models;

import lombok.*;

/**
 * The {@code OperationDomain} class represents the domain model for an operation.
 * It encapsulates the input parameters (x, y, n) and the result of the calculation.
 * This class utilizes Lombok annotations for boilerplate code like getters, setters,
 * and the builder pattern, improving readability and maintainability.
 *
 * The class is designed to be immutable with respect to the input parameters (x, y, n),
 * ensuring that these values cannot be altered once an instance is created, thus
 * maintaining the integrity of the operation data.
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class Operation {
    private final Long x;
    private final Long y;
    private final Long n;
    private Long result;

    /**
     * Sets the result of the operation. This method allows updating the result
     * field while keeping the input parameters (x, y, n) immutable.
     *
     * @param result The result of the operation to be set.
     */
    public void setResult(Long result) {
        this.result = result;
    }
}
