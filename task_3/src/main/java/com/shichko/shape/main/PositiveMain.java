package com.shichko.shape.main;

import com.shichko.shape.creator.EllipseCreator;
import com.shichko.shape.entity.Ellipse;
import com.shichko.shape.entity.Point;
import com.shichko.shape.entity.Warehouse;
import com.shichko.shape.exception.EllipseException;
import com.shichko.shape.filler.EllipseRepositoryFiller;
import com.shichko.shape.filler.EllipseWarehouseFiller;
import com.shichko.shape.parser.EllipseParser;
import com.shichko.shape.reader.EllipseFileReader;
import com.shichko.shape.repository.EllipseRepository;
import com.shichko.shape.repository.Specification;
import com.shichko.shape.repository.impl.IdSpecification;

import java.util.ArrayList;
import java.util.List;

public class PositiveMain {
    public static void main(String[] args) throws EllipseException {
        EllipseFileReader fileReader = new EllipseFileReader();
        List<String> lines = fileReader.readLinesFromFile("src/main/resources/data/ellipses.txt");
        List<Ellipse> ellipses = new ArrayList<>();
        EllipseParser parser = new EllipseParser();
        for (String coordinatesLine: lines) {
            double[] ellipseCoords = parser.parse(coordinatesLine);
            Ellipse ellipse = EllipseCreator.createFromCoordinatesArray(ellipseCoords);
            ellipses.add(ellipse);
        }

        EllipseRepository repository = EllipseRepositoryFiller.fillFromEllipseList(ellipses);
        Warehouse warehouse = EllipseWarehouseFiller.fillFromRepository(repository);
        long id = 1;
        double firstEllipseAreaBeforeChange = warehouse.get(id).getArea();

        System.out.println("\nArea from warehouse before change: " + firstEllipseAreaBeforeChange);

        Specification idSpecification = new IdSpecification(id);
        Ellipse firstEllipse = repository.query(idSpecification).get(0);
        Point newSecondPoint = new Point(2, -2);
        firstEllipse.setSecondPoint(newSecondPoint);

        double firstEllipseAreaAfterChange = warehouse.get(id).getArea();

        System.out.println("\nArea from warehouse after change: " + firstEllipseAreaAfterChange);

    }
}
