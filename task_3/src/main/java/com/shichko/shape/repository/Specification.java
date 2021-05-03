package com.shichko.shape.repository;

import com.shichko.shape.entity.Ellipse;

@FunctionalInterface
public interface Specification {
    boolean specify(Ellipse ellipse);
}
