package com.shichko.shape.filler;

import com.shichko.shape.entity.EllipseParameters;
import com.shichko.shape.entity.Warehouse;
import com.shichko.shape.observer.EllipseObserver;
import com.shichko.shape.observer.impl.EllipseObserverImpl;
import com.shichko.shape.repository.EllipseRepository;
import com.shichko.shape.service.EllipseService;
import com.shichko.shape.service.impl.EllipseServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EllipseWarehouseFiller {

    private final static Logger logger = LogManager.getLogger();

    private EllipseWarehouseFiller() { }

    public static Warehouse fillFromRepository(EllipseRepository repository) {
        Warehouse warehouse = Warehouse.getInstance();
        EllipseService service = new EllipseServiceImpl();
        EllipseObserver observer = new EllipseObserverImpl();

        repository.forEach(ellipse -> {
            ellipse.attach(observer);

            double perimeter = service.perimeter(ellipse);
            double area = service.area(ellipse);
            EllipseParameters parameters = new EllipseParameters(perimeter, area);

            long ellipseId = ellipse.getEllipseId();

            warehouse.put(ellipseId, parameters);
        });
        logger.log(Level.INFO, "Filled Warehouse " + warehouse + " from repository " + repository);
        return warehouse;
    }
}
