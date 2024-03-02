package com.inclusioncloud.maxinteger.infrastructure.entrypoints.controller;


import com.inclusioncloud.maxinteger.application.usecase.CalculateUseCase;
import com.inclusioncloud.maxinteger.application.usecase.ResultsUseCase;
import com.inclusioncloud.maxinteger.config.exception.ErrorResponse;
import com.inclusioncloud.maxinteger.config.exception.SPError;
import com.inclusioncloud.maxinteger.infrastructure.entrypoints.model.OperationRequest;
import com.inclusioncloud.maxinteger.infrastructure.entrypoints.model.OperationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The {@code OperationsController} class handles incoming REST API requests related to operations.
 * It manages endpoints for calculating the maximum non-negative integer k based on given parameters
 * and for retrieving all past calculation results.
 */
@RestController
@RequestMapping("/operation")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {})
public class OperationsController {

    private static final Logger logger = LoggerFactory.getLogger(OperationsController.class);

    private final CalculateUseCase calculateUseCase;
    private final ResultsUseCase resultUseCase;

    /**
     * Creates an instance of {@code OperationsController} with necessary ports.
     *
     * @param calculateUseCase Port for operation calculations.
     * @param resultUseCase Port for retrieving operation results.
     */
    public OperationsController(CalculateUseCase calculateUseCase, ResultsUseCase resultUseCase) {
        this.calculateUseCase = calculateUseCase;
        this.resultUseCase = resultUseCase;
    }

    /**
     * Endpoint for calculating the maximum non-negative integer k. Validates the request and
     * delegates the calculation to the application service.
     *
     * @param request Contains x, y, and n values for the operation.
     * @param bindingResult Captures validation results for the request.
     * @return ResponseEntity containing the result or error.
     */
    @Operation(
            summary = "Calculate the maximum integer",
            description = "Calculates the maximum non-negative integer k such that 0≤k≤n and k mod x = y. Returns the calculated value or validation errors.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Calculation successful",
                            content = @Content(schema = @Schema(implementation = OperationResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input parameters or calculation error",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )

    @PostMapping
    public ResponseEntity<Object> calculate(@Valid @RequestBody OperationRequest request, BindingResult bindingResult) {
        try {
            logger.info("Received calculation request: {}", request);
            if (bindingResult.hasErrors()) {
                logger.error("Validation errors: {}", bindingResult.getFieldErrors());
                return ResponseEntity.badRequest().body(OperationResponse.badRequest(bindingResult));
            }

            com.inclusioncloud.maxinteger.domain.models.Operation result = this.calculateUseCase.calculate(request.toDomain());
            return ResponseEntity.status(HttpStatus.CREATED).body(OperationResponse.of(result, HttpStatus.CREATED));
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse(false, SPError.OPERATION_CONTROLLER_ERROR_CALCULATE.getErrorCode(), SPError.OPERATION_CONTROLLER_ERROR_CALCULATE.getErrorMessage(), ex.getCause());
            logger.error("Calculation error: {}", errorResponse, ex);
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    /**
     * Endpoint for retrieving all past calculation results.
     *
     * @return ResponseEntity containing a list of results or an error.
     */
    @Operation(
            summary = "Retrieve all calculation results",
            description = "Fetches all the past results of the calculation operations.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Results retrieved successfully",
                            content = @Content(schema = @Schema(implementation = List.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Error occurred while fetching the results",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<Object> result() {
        try {
            logger.info("Fetching all results");
            List<com.inclusioncloud.maxinteger.domain.models.Operation> results = this.resultUseCase.getResults();
            return ResponseEntity.ok(OperationResponse.of(results, HttpStatus.OK));
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse(false, SPError.OPERATION_CONTROLLER_ERROR_CALCULATE.getErrorCode(), SPError.OPERATION_CONTROLLER_ERROR_CALCULATE.getErrorMessage(), ex.getCause());
            logger.error("Error fetching results: {}", errorResponse, ex);
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
