package com.shichko.multithreading.reader;

import com.shichko.multithreading.exception.ShipException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ShipFileReader {

    private static final Logger logger = LogManager.getLogger();

    public String readFileAsString(Path filePath) throws ShipException {
        try {
            String shipsJson = new String(Files.readAllBytes(filePath));
            logger.log(Level.INFO, "Read from file: " + filePath + " ships: " + shipsJson);
            return shipsJson;
        } catch (IOException e) {
            logger.log(Level.ERROR, "Failed to read from file: " + filePath, e);
            throw new ShipException("Failed to read from file: " + filePath, e);
        }
    }

}
