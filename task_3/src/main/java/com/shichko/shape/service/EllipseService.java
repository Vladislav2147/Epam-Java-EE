package com.shichko.shape.service;

import com.shichko.shape.entity.Ellipse;

public interface EllipseService {
    boolean isCircle(Ellipse ellipse);
    boolean isEllipse(Ellipse ellipse);

    double perimeter(Ellipse ellipse);
    double square(Ellipse ellipse);
}
