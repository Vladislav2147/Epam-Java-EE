package com.shichko.shape.repository.impl;

import com.shichko.shape.entity.Ellipse;
import com.shichko.shape.repository.Specification;
import com.shichko.shape.service.EllipseService;
import com.shichko.shape.service.impl.EllipseServiceImpl;

public class CircleSpecification implements Specification {
    @Override
    public boolean specify(Ellipse ellipse) {
        EllipseService service = new EllipseServiceImpl();
        boolean result = service.isCircle(ellipse);
        return result;
    }
}
