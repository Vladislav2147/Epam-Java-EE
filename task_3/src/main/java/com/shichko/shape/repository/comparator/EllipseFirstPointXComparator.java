package com.shichko.shape.repository.comparator;

import com.shichko.shape.entity.Ellipse;

import java.util.Comparator;

public class EllipseFirstPointXComparator implements Comparator<Ellipse> {
    @Override
    public int compare(Ellipse ellipse1, Ellipse ellipse2) {
        return Double.compare(ellipse1.getFirstPoint().getX(), ellipse2.getFirstPoint().getX());
    }
}
