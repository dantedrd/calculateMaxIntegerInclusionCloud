package com.inclusioncloud.maxinteger.config.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Custom exception class for handling database-related errors.
 * Extends {@link GenericException} to provide specific error handling for database operations.
 *
 * <p>This class includes additional logging to record the occurrence of database errors,
 * capturing the error code, message, and optionally the cause, providing valuable information for debugging and error tracking.</p>
 *
 * <p>Instances of this exception should be thrown in scenarios involving database access errors,
 * such as connection failures, query execution errors, or data manipulation issues, ensuring a consistent approach
 * to error handling in database operations.</p>
 */
public class DataBaseException extends GenericException {

    private static final Logger logger = LoggerFactory.getLogger(DataBaseException.class);

    /**
     * Constructs a new DataBaseException with the specified detail message, error code, and cause.
     * Additionally, logs the error details for diagnostic purposes.
     *
     * @param errorCode The error code associated with this exception.
     * @param message The detailed message for this exception.
     * @param cause The cause of this exception (a null value is permitted, and indicates that the cause is nonexistent or unknown).
     */
    public DataBaseException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
        logger.error("Exception DataBaseException: code:{}, message:{}, cause:{}", errorCode, message, cause.getCause());
    }

    /**
     * Constructs a new DataBaseException with the specified detail message and error code.
     * Additionally, logs the error message and code for diagnostic purposes.
     *
     * @param errorCode The error code associated with this exception.
     * @param message The detailed message for this exception.
     */
    public DataBaseException(int errorCode, String message) {
        super(errorCode, message);
        logger.error("Exception DataBaseException: code:{}, message:{}", errorCode, message);
    }
}
