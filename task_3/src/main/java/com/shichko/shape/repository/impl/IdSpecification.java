package com.shichko.shape.repository.impl;

import com.shichko.shape.entity.Ellipse;
import com.shichko.shape.repository.Specification;

public class IdSpecification implements Specification {

    private int id;

    public IdSpecification(int id) {
        this.id = id;
    }

    @Override
    public boolean specify(Ellipse ellipse) {
        boolean result = ellipse.getEllipseId() == id;
        return result;
    }
}
