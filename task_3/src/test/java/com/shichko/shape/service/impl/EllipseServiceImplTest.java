package com.shichko.shape.service.impl;

import com.shichko.shape.entity.Ellipse;
import com.shichko.shape.entity.Point;
import com.shichko.shape.service.EllipseService;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class EllipseServiceImplTest {

    private EllipseService service;

    @BeforeTest
    public void initService() {
        service = new EllipseServiceImpl();
    }

    @Test
    public void testIsCircleReturnsTrueIfEllipseIsCircle() {
        Point firstPoint = new Point(-1, 1);
        Point secondPoint = new Point(1, -1);
        Ellipse circle = new Ellipse(firstPoint, secondPoint);

        boolean result = service.isCircle(circle);
        assertTrue(result);
    }

    @Test
    public void testIsCircleReturnsFalseIfEllipseIsNotCircle() {
        Point firstPoint = new Point(-1, 1);
        Point secondPoint = new Point(1, -3);
        Ellipse notCircle = new Ellipse(firstPoint, secondPoint);

        boolean result = service.isCircle(notCircle);
        assertFalse(result);
    }

    @Test
    public void testIsEllipseReturnsTrueIfEllipseIsNotFlat() {
        Point firstPoint = new Point(-1, 1);
        Point secondPoint = new Point(1, -3);
        Ellipse ellipse = new Ellipse(firstPoint, secondPoint);

        boolean result = service.isEllipse(ellipse);
        assertTrue(result);
    }

    @Test
    public void testIsEllipseReturnsFalseIfEllipseIsFlat() {
        Point firstPoint = new Point(-1, 1);
        Point secondPoint = new Point(-1, -3);
        Ellipse flatEllipse = new Ellipse(firstPoint, secondPoint);

        boolean result = service.isEllipse(flatEllipse);
        assertFalse(result);
    }

    @Test
    public void testIsIntersectAnyAxisOnLengthReturnsTrueIfIntersects() {
        Point firstPoint = new Point(1, 1);
        Point secondPoint = new Point(5, -1);
        double length = secondPoint.getX() - firstPoint.getX();
        Ellipse ellipseIntersectsX = new Ellipse(firstPoint, secondPoint);

        boolean result = service.isIntersectAnyAxisOnLength(ellipseIntersectsX, length);
        assertTrue(result);
    }

    @Test
    public void testIsIntersectAnyAxisOnLengthReturnsFalseIfNotIntersects() {
        Point firstPoint = new Point(1, 4);
        Point secondPoint = new Point(5, 2);
        double length = secondPoint.getX() - firstPoint.getX();
        Ellipse ellipseNotIntersects = new Ellipse(firstPoint, secondPoint);

        boolean result = service.isIntersectAnyAxisOnLength(ellipseNotIntersects, length);
        assertFalse(result);
    }

    @Test
    public void testIsPointInsideEllipseReturnsTrueIfPointInside() {
        Point firstPoint = new Point(-1, 1);
        Point secondPoint = new Point(1, -1);
        Point pointInsideEllipse = new Point(0, 0);
        Ellipse ellipse = new Ellipse(firstPoint, secondPoint);

        boolean result = service.isPointInsideEllipse(ellipse, pointInsideEllipse);
        assertTrue(result);
    }

    @Test
    public void testIsPointInsideEllipseReturnsFalseIfPointOutside() {
        Point firstPoint = new Point(-1, 1);
        Point secondPoint = new Point(1, -1);
        Point pointOutsideEllipse = new Point(1, 1);
        Ellipse ellipse = new Ellipse(firstPoint, secondPoint);

        boolean result = service.isPointInsideEllipse(ellipse, pointOutsideEllipse);
        assertFalse(result);
    }
}