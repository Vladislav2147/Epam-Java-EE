package com.shichko.shape.parser;

import com.shichko.shape.exception.EllipseException;
import com.shichko.shape.validator.EllipseStringValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class EllipseParser {

    private final static Logger logger = LogManager.getLogger();

    private final static String SPACE_DELIMITER_REGEX = "\\s";

    public double[] parse(String ellipseCoordsString) throws EllipseException {
        if (!EllipseStringValidator.isCoordinateStringValid(ellipseCoordsString)) {
            logger.log(Level.ERROR, "data \"" + ellipseCoordsString + "\" is not valid");
            throw new EllipseException("data \"" + ellipseCoordsString + "\" is not valid");
        }

        String[] values = ellipseCoordsString.split(SPACE_DELIMITER_REGEX);
        double[] coordinatesArray = new double[values.length];

        for (int i = 0; i < values.length; i++) {
            coordinatesArray[i] = Double.parseDouble(values[i]);
        }

        logger.log(Level.INFO, "parsed double array: " + Arrays.toString(coordinatesArray));
        return coordinatesArray;
    }
}
