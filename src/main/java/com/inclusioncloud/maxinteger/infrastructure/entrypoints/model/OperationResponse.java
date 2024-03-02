package com.inclusioncloud.maxinteger.infrastructure.entrypoints.model;



import com.inclusioncloud.maxinteger.config.exception.SPError;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Custom response wrapper specifically for operation-related responses.
 * Extends {@link GenericResponse} to include additional helper methods for creating standardized responses.
 */
public class OperationResponse extends GenericResponse {

    /**
     * Creates a successful operation response with the given result.
     *
     * @param result     The result of the operation to be included in the response.
     * @param httpStatus The HTTP status associated with the response.
     * @return An instance of {@link OperationResponse} populated with success details and the operation result.
     */
    public static OperationResponse of(Object result, HttpStatus httpStatus) {
        OperationResponse response = new OperationResponse();
        response.setCodeStatus(httpStatus.value());
        response.setMessage(httpStatus.getReasonPhrase());
        response.setData(result);
        return response;
    }

    /**
     * Creates an error response based on the provided {@link BindingResult}.
     * This is typically used to report validation errors in client requests.
     *
     * @param bindingResult The result of a validation check, containing field errors.
     * @return An instance of {@link OperationResponse} populated with error details and validation messages.
     */
    public static OperationResponse badRequest(BindingResult bindingResult) {
        List<String> errors = bindingResult.getFieldErrors()
                .stream()
                .map(error -> String.format("Field: %s -> Error: %s", error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        OperationResponse response = new OperationResponse();
        response.setCodeStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(SPError.INVALID_PARAMS_ERROR.getErrorMessage());
        response.setData(errors);

        return response;
    }
}
