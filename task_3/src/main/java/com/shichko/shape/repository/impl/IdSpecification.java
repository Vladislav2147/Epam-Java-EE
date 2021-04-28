package com.shichko.shape.repository.impl;

import com.shichko.shape.entity.Ellipse;
import com.shichko.shape.repository.Specification;

public class IdSpecification implements Specification {

    private long id;

    public IdSpecification(long id) {
        this.id = id;
    }

    @Override
    public boolean specify(Ellipse ellipse) {
        boolean result = ellipse.getEllipseId() == id;
        return result;
    }
}
