package com.shichko.shape.service.impl;

import com.shichko.shape.entity.Ellipse;
import com.shichko.shape.service.EllipseService;

public class EllipseServiceImpl implements EllipseService {

    public boolean isCircle(Ellipse ellipse) {
        double axisX = getAxisX(ellipse);
        double axisY = getAxisY(ellipse);
        return axisX == axisY;
    }

    public boolean isEllipse(Ellipse ellipse) {
        double axisX = getAxisX(ellipse);
        double axisY = getAxisY(ellipse);
        return axisX == 0 || axisY == 0;
    }

    public double perimeter(Ellipse ellipse) {
        double semiAxisX = getAxisX(ellipse) / 2;
        double semiAxisY = getAxisY(ellipse) / 2;

        double perimeter = 4 * (Math.PI * semiAxisX * semiAxisY + Math.pow(semiAxisX - semiAxisY, 2))
                / semiAxisX + semiAxisY;
        return perimeter;
    }

    public double square(Ellipse ellipse) {
        double semiAxisX = getAxisX(ellipse) / 2;
        double semiAxisY = getAxisY(ellipse) / 2;

        double square = semiAxisX * semiAxisY * Math.PI;
        return square;
    }

    private double getAxisX(Ellipse ellipse) {
        double axisX = Math.abs(ellipse.getFirstPoint().getX() - ellipse.getSecondPoint().getX());
        return axisX;
    }

    private double getAxisY(Ellipse ellipse) {
        double axisY = Math.abs(ellipse.getFirstPoint().getY() - ellipse.getSecondPoint().getY());
        return axisY;
    }
}
