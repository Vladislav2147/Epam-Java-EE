package com.shichko.multithreading.reader;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class PortPropertiesReader {
    private static final Logger logger = LogManager.getLogger();
    public static final int DOCKS_AMOUNT;
    public static final int PORT_CAPACITY;
    public static final double LOAD_FACTOR;
    public static final int PROCESSING_TIME_MILLIS;

    static {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("settings/port");
            DOCKS_AMOUNT = Integer.parseInt(resourceBundle.getString("docksAmount"));
            PORT_CAPACITY = Integer.parseInt(resourceBundle.getString("portCapacity"));
            LOAD_FACTOR = Double.parseDouble(resourceBundle.getString("loadFactor"));
            PROCESSING_TIME_MILLIS = Integer.parseInt(resourceBundle.getString("processingTimeMillis"));
        } catch (MissingResourceException e) {
            logger.log(Level.FATAL, e);
            throw new ExceptionInInitializerError("MissingResourceException: " + e.getMessage());
        }
    }

    private PortPropertiesReader() {
    }
}
