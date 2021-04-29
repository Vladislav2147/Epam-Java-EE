package com.shichko.shape.creator;

import com.shichko.shape.entity.Ellipse;
import com.shichko.shape.entity.Point;
import com.shichko.shape.exception.EllipseException;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class EllipseCreatorTest {

    @Test
    public void testCreateFromCoordinatesArrayValidReturnsEllipse() throws EllipseException {
        double[] validCoordinates = {-5.3, 5, 5.6, -5};

        Ellipse ellipse = EllipseCreator.createFromCoordinatesArray(validCoordinates);

        assertNotNull(ellipse);
    }

    @Test(expectedExceptions = EllipseException.class)
    public void testCreateFromCoordinatesArrayInvalidThrowsEllipseException() throws EllipseException {
        double[] invalidCoordinates = {5.6, -5, -5.3, 5};

        EllipseCreator.createFromCoordinatesArray(invalidCoordinates);
    }

    @Test
    public void testCreateFromPointsValidReturnsEllipse() throws EllipseException {
        Point validFirstPoint = new Point(-6, 6);
        Point validSecondPoint = new Point(6, -3.4);

        Ellipse ellipse = EllipseCreator.createFromPoints(validFirstPoint, validSecondPoint);

        assertNotNull(ellipse);
    }

    @Test(expectedExceptions = EllipseException.class)
    public void testCreateFromPointsInvalidThrowsEllipseException() throws EllipseException {
        Point invalidFirstPoint = new Point(6, -3.4);
        Point invalidSecondPoint = new Point(-6, 6);

        EllipseCreator.createFromPoints(invalidFirstPoint, invalidSecondPoint);
    }
}