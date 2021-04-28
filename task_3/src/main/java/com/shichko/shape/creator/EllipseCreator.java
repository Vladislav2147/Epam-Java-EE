package com.shichko.shape.creator;

import com.shichko.shape.entity.Ellipse;
import com.shichko.shape.entity.Point;
import com.shichko.shape.exception.EllipseException;
import com.shichko.shape.util.IdGenerator;
import com.shichko.shape.validator.EllipseDataValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EllipseCreator {

    private final static Logger logger = LogManager.getLogger();

    private EllipseCreator() { }

    public static Ellipse createFromCoordinatesArray(double[] coordinates) throws EllipseException {
        if (coordinates.length != 4) {
            throw new EllipseException("Ellipse can be created only from 4 coordinates, but have: " + coordinates.length);
        }
        Point firstPoint = new Point(coordinates[0], coordinates[1]);
        Point secondPoint = new Point(coordinates[2], coordinates[3]);

        Ellipse ellipse = createFromPoints(firstPoint, secondPoint);
        return ellipse;
    }

    public static Ellipse createFromPoints(Point firstPoint, Point secondPoint) throws EllipseException {
        Ellipse ellipse = new Ellipse(firstPoint, secondPoint);
        long ellipseId = IdGenerator.generateId();
        ellipse.setEllipseId(ellipseId);

        if (!EllipseDataValidator.isEllipseValid(ellipse)) {
            throw new EllipseException("First point should be top left and Second point bottom right: " + ellipse);
        }

        logger.log(Level.INFO, "Created Ellipse " + ellipse);
        return ellipse;
    }
}
