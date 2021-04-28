package com.shichko.shape.creator;

import com.shichko.shape.entity.Ellipse;
import com.shichko.shape.exception.EllipseException;
import com.shichko.shape.parser.EllipseParser;
import com.shichko.shape.repository.EllipseRepository;
import com.shichko.shape.repository.impl.EllipseRepositoryImpl;
import com.shichko.shape.validator.EllipseStringValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class EllipseRepositoryCreator {

    private final static Logger logger = LogManager.getLogger();

    private EllipseRepositoryCreator() { }

    public static EllipseRepository createFromEllipseList(List<Ellipse> ellipseList) {
        EllipseRepository repository = new EllipseRepositoryImpl();
        repository.addAll(ellipseList);
        logger.log(Level.INFO, "Created EllipseRepository " + repository);
        return repository;
    }

    public static EllipseRepository createFromStringList(List<String> values) throws EllipseException {
        EllipseParser parser = new EllipseParser();
        EllipseRepository repository = new EllipseRepositoryImpl();

        for (String value: values) {
            if (!EllipseStringValidator.isCoordinateStringValid(value)) {
                throw new EllipseException("Coordinate string \"" + value + "\" is not valid");
            }
            double[] coordinates = parser.parse(value);
            Ellipse ellipse = EllipseCreator.createFromCoordinatesArray(coordinates);
            repository.add(ellipse);
        }

        logger.log(Level.INFO, "Created EllipseRepository " + repository);
        return repository;
    }

}
