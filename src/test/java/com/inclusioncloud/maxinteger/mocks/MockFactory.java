package com.inclusioncloud.maxinteger.mocks;


import com.inclusioncloud.maxinteger.config.exception.CustomException;
import com.inclusioncloud.maxinteger.config.exception.DataBaseException;
import com.inclusioncloud.maxinteger.config.exception.SPError;
import com.inclusioncloud.maxinteger.domain.models.Operation;
import com.inclusioncloud.maxinteger.infrastructure.entrypoints.model.OperationRequest;

/**
 * The {@code MockFactory} class is a utility class for creating mock objects for testing.
 * It provides static methods to generate various mock objects and data structures,
 * commonly used across different test cases.
 *
 * <p>This class is essential for unit tests, where predefined inputs and expected
 * outputs are necessary to validate the behavior of the components under test.</p>
 */
public class MockFactory {

    private static final Long x = 10L;
    private static final Long y = 5L;
    private static final Long n = 15L;
    private static final Long result = 15L;

    /**
     * Returns a JSON string representing a valid operation request.
     *
     * @return A valid JSON string for operation request.
     */
    public static String getJsonContentValid(){
        return "{\"x\":5,\"y\":0,\"n\":4}";
    }

    /**
     * Returns a JSON string representing an invalid operation request.
     *
     * @return An invalid JSON string for operation request.
     */
    public static String getJsonContentInvalid(){
        return "{\"x\":5,\"y\":0}";
    }

    /**
     * Creates and returns an instance of {@link OperationRequest} with valid predefined values.
     *
     * @return A mock {@link OperationRequest} object with valid data.
     */
    public static OperationRequest getOperationRequestFactory(){
        OperationRequest mock = new OperationRequest();
        mock.setX(x);
        mock.setY(y);
        mock.setN(n);
        return mock;
    }

    /**
     * Creates and returns an instance of {@link OperationRequest} with incomplete data, simulating an invalid request.
     *
     * @return A mock {@link OperationRequest} object with invalid/incomplete data.
     */
    public static OperationRequest getOperationRequestInvalidFactory(){
        OperationRequest mock = new OperationRequest();
        mock.setX(x);
        mock.setY(y);
        // 'n' is intentionally omitted to simulate an invalid mock
        return mock;
    }

    /**
     * Creates and returns a fully populated {@link Operation} object with predefined values.
     *
     * @return A mock {@link Operation} object.
     */
    public static Operation getOperationDomainFactory(){
        return Operation.builder()
                .x(x)
                .y(y)
                .n(n)
                .result(result).build();
    }

    /**
     * Creates and returns a {@link Operation} object simulating a borderline case.
     *
     * @return A mock {@link Operation} object for borderline case.
     */
    public static Operation getOperationDomainBorderFactory(){
        return Operation.builder()
                .x(x)
                .y(0L)
                .n(20L).build();
    }

    /**
     * Creates and returns a {@link Operation} object with invalid values.
     *
     * @return A mock {@link Operation} object with invalid values.
     */
    public static Operation getOperationDomainInvalidFactory(){
        return Operation.builder()
                .x(-1L)
                .y(-1L)
                .n(-1L).build();
    }

    /**
     * Creates and returns a {@link CustomException} object, simulating an exception for use case scenarios.
     *
     * @return A mock {@link CustomException} object.
     */
    public static CustomException getCustomExceptionUseCaseFactory(){
        return new CustomException(SPError.OPERATION_USE_CASE_ERROR_CALCULATE.getErrorCode(), SPError.OPERATION_USE_CASE_ERROR_CALCULATE.getErrorMessage(), new Throwable("Default cause"));
    }

    /**
     * Creates and returns a {@link CustomException} object, simulating an exception for service layer scenarios.
     *
     * @return A mock {@link CustomException} object for service layer.
     */
    public static CustomException getCustomExceptionServiceFactory(){
        return new CustomException(SPError.OPERATION_SERVICE_ERROR_CALCULATE.getErrorCode(), SPError.OPERATION_SERVICE_ERROR_CALCULATE.getErrorMessage(), new Throwable("Default cause"));
    }

    /**
     * Creates and returns a {@link DataBaseException} object, simulating a database-related exception.
     *
     * @return A mock {@link DataBaseException} object.
     */
    public static DataBaseException getDataBaseExceptionFactory(){
        return new DataBaseException(SPError.OPERATION_ADAPTER_FIND_ERROR.getErrorCode(), SPError.OPERATION_ADAPTER_FIND_ERROR.getErrorMessage(), new Throwable("Default cause"));
    }
}
