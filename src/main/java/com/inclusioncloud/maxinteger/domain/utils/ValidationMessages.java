package com.inclusioncloud.maxinteger.domain.utils;

/**
 * The {@code ValidationMessages} class provides a centralized repository of constant
 * messages used for validation throughout the application. This class follows the
 * utility class pattern, where it only contains public static final constants and
 * has a private constructor to prevent instantiation.
 *
 * These messages are primarily used in input validation checks to provide clear and
 * consistent feedback about validation failures.
 */
public class ValidationMessages {

    public static final String NOT_NULL_X = "X no puede ser null";
    public static final String NOT_NULL_Y = "Y no puede ser null";
    public static final String NOT_NULL_N = "N no puede ser null";
    public static final String MIN_VALUE_X = "X debe ser mayor a -1";
    public static final String MIN_VALUE_Y = "Y debe ser mayor a -1";
    public static final String MIN_VALUE_N = "N debe ser mayor a -1";
    public static final String MAX_VALUE_X = "X supera el valor máximo permitido " + Long.MAX_VALUE;
    public static final String MAX_VALUE_Y = "Y supera el valor máximo permitido " + Long.MAX_VALUE;
    public static final String MAX_VALUE_N = "N supera el valor máximo permitido " + Long.MAX_VALUE;

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private ValidationMessages() {
        // This constructor is intentionally empty. Nothing special is needed here.
    }
}
