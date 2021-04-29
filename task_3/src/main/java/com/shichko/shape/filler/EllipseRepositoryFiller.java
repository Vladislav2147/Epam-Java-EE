package com.shichko.shape.filler;

import com.shichko.shape.creator.EllipseCreator;
import com.shichko.shape.entity.Ellipse;
import com.shichko.shape.exception.EllipseException;
import com.shichko.shape.parser.EllipseParser;
import com.shichko.shape.repository.EllipseRepository;
import com.shichko.shape.repository.impl.EllipseRepositoryImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class EllipseRepositoryFiller {

    private final static Logger logger = LogManager.getLogger();

    private EllipseRepositoryFiller() { }

    public static EllipseRepository fillFromEllipseList(List<Ellipse> ellipseList) {
        EllipseRepository repository = EllipseRepositoryImpl.getInstance();
        repository.addAll(ellipseList);
        logger.log(Level.INFO, "Created EllipseRepository " + repository);
        return repository;
    }

    public static EllipseRepository fillFromStringList(List<String> values) throws EllipseException {
        EllipseParser parser = new EllipseParser();
        EllipseRepository repository = EllipseRepositoryImpl.getInstance();

        for (String value: values) {
            double[] coordinates = parser.parse(value);
            Ellipse ellipse = EllipseCreator.createFromCoordinatesArray(coordinates);
            repository.add(ellipse);
        }

        logger.log(Level.INFO, "Created EllipseRepository " + repository);
        return repository;
    }

}
