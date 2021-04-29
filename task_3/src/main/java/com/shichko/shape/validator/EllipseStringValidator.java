package com.shichko.shape.validator;

public class EllipseStringValidator {

    private EllipseStringValidator() { }

    private final static String ELLIPSE_COORDINATES_REGEXP = "^((-?\\d+\\.?\\d*)\\s){3}(-?\\d+\\.?\\d*)$";

    public static boolean isCoordinateStringValid(String ellipseCoordinatesString) {
        return ellipseCoordinatesString.matches(ELLIPSE_COORDINATES_REGEXP);
    }
}
