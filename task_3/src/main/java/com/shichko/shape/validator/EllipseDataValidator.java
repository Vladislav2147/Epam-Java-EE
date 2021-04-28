package com.shichko.shape.validator;

import com.shichko.shape.entity.Ellipse;
import com.shichko.shape.entity.Point;

public class EllipseDataValidator {
    
    private EllipseDataValidator() { }

    public static boolean isEllipseValid(Ellipse ellipse) {
        Point firstPoint = ellipse.getFirstPoint();
        Point secondPoint = ellipse.getSecondPoint();

        return secondPoint.getX() - firstPoint.getX() > 0 && firstPoint.getY() - secondPoint.getY() > 0;
    }
}
