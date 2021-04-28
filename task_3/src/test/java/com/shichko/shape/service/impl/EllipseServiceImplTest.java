package com.shichko.shape.service.impl;

import com.shichko.shape.entity.Ellipse;
import com.shichko.shape.entity.Point;
import com.shichko.shape.service.EllipseService;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.*;

public class EllipseServiceImplTest {

    EllipseService service = new EllipseServiceImpl();

    @Test
    public void testIsCircle() {
    }

    @Test
    public void testIsEllipse() {
    }

    @Test
    public void testPerimeter() {

    }

    @Test
    public void testSquare() {
    }

    @Test
    public void testIsIntersectAxisOnLength() {
        Ellipse ellipse = new Ellipse(1, new Point(-2, 2), new Point(2, -2));
        assertTrue(service.isIntersectAnyAxisOnLength(ellipse, 4));
    }
}