package com.shichko.shape.validator;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class EllipseStringValidatorTest {

    @Test
    public void testIsCoordinateStringValidReturnsTrueOnValidString() {
        String validCoordinatesString = "-0.4 0.7777 5 12.5";

        boolean result = EllipseStringValidator.isCoordinateStringValid(validCoordinatesString);
        assertTrue(result);
    }

    @Test
    public void testIsCoordinateStringValidReturnsFalseOnMoreThenFourCoordsString() {
        String moreThenFourCoordsString = "-0.4 0.7777 5 12.5 15";

        boolean result = EllipseStringValidator.isCoordinateStringValid(moreThenFourCoordsString);
        assertFalse(result);
    }

    @Test
    public void testIsCoordinateStringValidReturnsFalseOnLessThenFourCoordsString() {
        String lessThenFourCoordsString = "-0.4 0.7777 5";

        boolean result = EllipseStringValidator.isCoordinateStringValid(lessThenFourCoordsString);
        assertFalse(result);
    }

    @Test
    public void testIsCoordinateStringValidReturnsFalseOnNonDoubleCoordsString() {
        String nonDoubleCoordsString = "-0.4 0.7777 5 abc";

        boolean result = EllipseStringValidator.isCoordinateStringValid(nonDoubleCoordsString);
        assertFalse(result);
    }
}