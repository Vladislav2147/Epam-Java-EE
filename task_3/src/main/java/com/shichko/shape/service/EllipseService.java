package com.shichko.shape.service;

import com.shichko.shape.entity.Ellipse;
import com.shichko.shape.entity.Point;

public interface EllipseService {
    boolean isCircle(Ellipse ellipse);
    boolean isEllipse(Ellipse ellipse);
    boolean isIntersectAnyAxisOnLength(Ellipse ellipse, double length);
    boolean isPointInsideEllipse(Ellipse ellipse, Point point);
    double perimeter(Ellipse ellipse);
    double square(Ellipse ellipse);

}
