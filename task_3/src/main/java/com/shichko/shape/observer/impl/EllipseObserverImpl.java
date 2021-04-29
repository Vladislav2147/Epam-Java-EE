package com.shichko.shape.observer.impl;

import com.shichko.shape.entity.Ellipse;
import com.shichko.shape.entity.Warehouse;
import com.shichko.shape.exception.EllipseException;
import com.shichko.shape.observer.EllipseEvent;
import com.shichko.shape.observer.EllipseObserver;
import com.shichko.shape.service.EllipseService;
import com.shichko.shape.service.impl.EllipseServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EllipseObserverImpl implements EllipseObserver {

    private final static Logger logger = LogManager.getLogger();

    @Override
    public void parameterChanged(EllipseEvent event) {
        Ellipse ellipse = event.getSource();
        EllipseService service = new EllipseServiceImpl();

        double perimeter = service.perimeter(ellipse);
        double area = service.area(ellipse);
        long id = ellipse.getEllipseId();

        try {
            Warehouse warehouse = Warehouse.getInstance();
            warehouse.updateParamsById(id, perimeter, area);
            logger.log(Level.INFO, "Successful updated params perimeter=" + perimeter + " area=" + area + " by id=" + id);
        } catch (EllipseException e) {
            logger.log(Level.ERROR, "EllipseException while updating params perimeter=" + perimeter + " area=" + area + " by id=" + id);
        }
    }
}
