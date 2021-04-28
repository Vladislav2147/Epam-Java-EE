package com.shichko.shape.observer.impl;

import com.shichko.shape.entity.Ellipse;
import com.shichko.shape.entity.EllipseParameters;
import com.shichko.shape.entity.Warehouse;
import com.shichko.shape.observer.EllipseEvent;
import com.shichko.shape.observer.EllipseObserver;
import com.shichko.shape.service.EllipseService;
import com.shichko.shape.service.impl.EllipseServiceImpl;

public class EllipseObserverImpl implements EllipseObserver {
    @Override
    public void parameterChanged(EllipseEvent event) {
        Ellipse ellipse = event.getSource();
        Warehouse warehouse = Warehouse.getInstance();
        EllipseService service = new EllipseServiceImpl();
        double perimeter = service.perimeter(ellipse);
        double area = service.area(ellipse);
        EllipseParameters parameters = new EllipseParameters(perimeter, area);
        long id  = ellipse.getEllipseId();
        warehouse.put(id, parameters);
    }
}
