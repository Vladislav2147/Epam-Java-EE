package com.shichko.shape.repository.impl;

import com.shichko.shape.entity.Ellipse;
import com.shichko.shape.entity.Point;
import com.shichko.shape.repository.Specification;
import com.shichko.shape.service.EllipseService;
import com.shichko.shape.service.impl.EllipseServiceImpl;

public class PointInsideEllipseSpecification implements Specification {

    private Point point;

    public PointInsideEllipseSpecification(Point point) {
        this.point = point;
    }

    @Override
    public boolean specify(Ellipse ellipse) {
        EllipseService service = new EllipseServiceImpl();
        boolean result = service.isPointInsideEllipse(ellipse, point);
        return result;
    }
}
