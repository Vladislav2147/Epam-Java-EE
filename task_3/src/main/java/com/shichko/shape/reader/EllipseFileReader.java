package com.shichko.shape.reader;

import com.shichko.shape.exception.EllipseException;
import com.shichko.shape.validator.EllipseStringValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class EllipseFileReader {

    private final static Logger logger = LogManager.getLogger();

    public List<String> readLinesFromFile(String filename) throws EllipseException {
        List<String> lines;
        Path filepath = Paths.get(filename);
        try {
            lines = Files
                    .lines(filepath)
                    .filter(EllipseStringValidator::isCoordinateStringValid)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error reading file " + filename, e);
            throw new EllipseException("Invalid filename: " + filename, e);
        }
        logger.log(Level.INFO, "Read lines " + lines + "from file " + filename);
        return lines;
    }
}
