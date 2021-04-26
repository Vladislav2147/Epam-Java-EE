package com.shichko.shape.repository;

import com.shichko.shape.entity.Ellipse;

public interface Specification {
    boolean specify(Ellipse ellipse);
}
