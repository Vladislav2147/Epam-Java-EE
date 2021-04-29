package com.shichko.shape.repository.comparator;

import com.shichko.shape.entity.Ellipse;
import com.shichko.shape.service.EllipseService;
import com.shichko.shape.service.impl.EllipseServiceImpl;

import java.util.Comparator;

public class EllipseAreaDescendingComparator implements Comparator<Ellipse> {
    private EllipseService service;

    public  EllipseAreaDescendingComparator() {
        service = new EllipseServiceImpl();
    }

    @Override
    public int compare(Ellipse ellipse1, Ellipse ellipse2) {
        double area1 = service.area(ellipse1);
        double area2 = service.area(ellipse2);
        return Double.compare(area2, area1);
    }
}
