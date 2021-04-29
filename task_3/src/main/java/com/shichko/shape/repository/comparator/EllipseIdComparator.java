package com.shichko.shape.repository.comparator;

import com.shichko.shape.entity.Ellipse;

import java.util.Comparator;

public class EllipseIdComparator implements Comparator<Ellipse> {
    @Override
    public int compare(Ellipse ellipse1, Ellipse ellipse2) {
        return Long.compare(ellipse1.getEllipseId(), ellipse2.getEllipseId());
    }
}
