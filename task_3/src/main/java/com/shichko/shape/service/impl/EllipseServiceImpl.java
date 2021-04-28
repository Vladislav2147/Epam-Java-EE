package com.shichko.shape.service.impl;

import com.shichko.shape.entity.Ellipse;
import com.shichko.shape.entity.Point;
import com.shichko.shape.service.EllipseService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EllipseServiceImpl implements EllipseService {

    private final static Logger logger = LogManager.getLogger();

    public boolean isCircle(Ellipse ellipse) {
        double axisX = getAxisXLength(ellipse);
        double axisY = getAxisYLength(ellipse);

        boolean isCircle = axisX == axisY;
        logger.log(Level.INFO, "Ellipse " + ellipse + " is circle: " + isCircle);
        return isCircle;
    }

    public boolean isEllipse(Ellipse ellipse) {
        double axisX = getAxisXLength(ellipse);
        double axisY = getAxisYLength(ellipse);

        boolean isEllipse = axisX != 0 && axisY != 0;
        logger.log(Level.INFO, "Ellipse " + ellipse + " is ellipse: " + isEllipse);
        return isEllipse;
    }

    public double perimeter(Ellipse ellipse) {
        double semiAxisX = getAxisXLength(ellipse) / 2;
        double semiAxisY = getAxisYLength(ellipse) / 2;

        double perimeter = 4 * (Math.PI * semiAxisX * semiAxisY + Math.pow(semiAxisX - semiAxisY, 2))
                / semiAxisX + semiAxisY;
        logger.log(Level.INFO, "Ellipse " + ellipse + " perimeter: " + perimeter);
        return perimeter;
    }

    public double area(Ellipse ellipse) {
        double semiAxisX = getAxisXLength(ellipse) / 2;
        double semiAxisY = getAxisYLength(ellipse) / 2;

        double area = semiAxisX * semiAxisY * Math.PI;
        logger.log(Level.INFO, "Ellipse " + ellipse + " area: " + area);
        return area;
    }

    public boolean isIntersectAnyAxisOnLength(Ellipse ellipse, double length) {
        double intersectionAxisYLength = getIntersectionAxisYLength(ellipse);
        logger.log(Level.INFO, "Ellipse " + ellipse + " intersects axis Y on length: " + intersectionAxisYLength);

        double intersectionAxisXLength = getIntersectionAxisXLength(ellipse);
        logger.log(Level.INFO, "Ellipse " + ellipse + " intersects axis X on length: " + intersectionAxisXLength);

        boolean isIntersectAnyAxisOnLength = length <= intersectionAxisXLength || length <= intersectionAxisYLength;
        logger.log(Level.INFO, "Ellipse " + ellipse + " intersects any axis on length >= " + length + ": " + isIntersectAnyAxisOnLength);
        return isIntersectAnyAxisOnLength;
    }

    @Override
    public boolean isPointInsideEllipse(Ellipse ellipse, Point point) {
        double a = getAxisXLength(ellipse) / 2;
        double b = getAxisYLength(ellipse) / 2;

        Point center = getEllipseCenter(ellipse);

        boolean isPointInsideEllipse = Math.pow((point.getX() - center.getX()) / a, 2)
                + Math.pow((point.getY() - center.getY()) / b, 2) < 1;
        logger.log(Level.INFO, "Point: " + point + "is inside Ellipse " + ellipse + ": " + isPointInsideEllipse);
        return isPointInsideEllipse;
    }

    private double getIntersectionAxisYLength(Ellipse ellipse) {
        double a = getAxisXLength(ellipse) / 2;
        double b = getAxisYLength(ellipse) / 2;

        Point center = getEllipseCenter(ellipse);

        double sqrt = Math.sqrt(Math.pow(b, 2) * (1 - Math.pow(0 - center.getX(), 2) / Math.pow(a, 2)));
        double intersectionLength;

        if (Double.isNaN(sqrt)) {
            intersectionLength = 0;
        } else {
            double positiveY = sqrt + center.getY();
            double negativeY = -sqrt + center.getY();

            intersectionLength = positiveY - negativeY;
        }

        return intersectionLength;
    }

    private double getIntersectionAxisXLength(Ellipse ellipse) {
        double a = getAxisXLength(ellipse) / 2;
        double b = getAxisYLength(ellipse) / 2;

        Point center = getEllipseCenter(ellipse);

        double sqrt = Math.sqrt(Math.pow(a, 2) * (1 - Math.pow(0 - center.getY(), 2) / Math.pow(b, 2)));
        double intersectionLength;

        if (Double.isNaN(sqrt)) {
            intersectionLength = 0;
        } else {
            double positiveX = sqrt + center.getX();
            double negativeX = -sqrt + center.getX();

            intersectionLength = positiveX - negativeX;
        }

        return intersectionLength;
    }

    private Point getEllipseCenter(Ellipse ellipse) {
        double centerX = (ellipse.getSecondPoint().getX() + ellipse.getFirstPoint().getX()) / 2;
        double centerY = (ellipse.getFirstPoint().getY() + ellipse.getSecondPoint().getY()) / 2;

        Point center = new Point(centerX, centerY);
        return center;
    }


    private double getAxisXLength(Ellipse ellipse) {
        double axisX = Math.abs(ellipse.getFirstPoint().getX() - ellipse.getSecondPoint().getX());
        return axisX;
    }

    private double getAxisYLength(Ellipse ellipse) {
        double axisY = Math.abs(ellipse.getFirstPoint().getY() - ellipse.getSecondPoint().getY());
        return axisY;
    }
}
