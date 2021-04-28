package com.shichko.shape.validator;

public class EllipseStringValidator {

    private EllipseStringValidator() { }

    private final static String ELLIPSE_COORDINATES_REGEXP = "^((-?\\d+\\.?\\d*)\\s){3}(-?\\d+\\.?\\d*)$";

    public static boolean isCoordinateStringValid(String ellipseString) {
        return ellipseString.matches(ELLIPSE_COORDINATES_REGEXP);
    }
}
