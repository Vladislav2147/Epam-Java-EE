package com.shichko.shape.validator;

import com.shichko.shape.entity.Ellipse;
import com.shichko.shape.entity.Point;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class EllipseDataValidatorTest {

    @Test
    public void testIsEllipseValidReturnsTrueOnValidEllipse() {
        Point validFirstPoint = new Point(-4, 5);
        Point validSecondPoint = new Point(4, -5);
        Ellipse validEllipse = new Ellipse(validFirstPoint, validSecondPoint);

        boolean result = EllipseDataValidator.isEllipseValid(validEllipse);
        assertTrue(result);
    }

    @Test
    public void testIsEllipseValidReturnsFalseOnInvalidEllipse() {
        Point validFirstPoint = new Point(-4, 5);
        Point invalidSecondPoint = new Point(4, 10);
        Ellipse invalidEllipse = new Ellipse(validFirstPoint, invalidSecondPoint);

        boolean result = EllipseDataValidator.isEllipseValid(invalidEllipse);
        assertFalse(result);
    }
}