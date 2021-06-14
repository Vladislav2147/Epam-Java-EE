package com.shichko.multithreading.main;

import com.shichko.multithreading.entity.Port;
import com.shichko.multithreading.entity.Ship;
import com.shichko.multithreading.exception.ShipException;
import com.shichko.multithreading.parser.ShipParser;
import com.shichko.multithreading.reader.ShipFileReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Paths;
import java.util.List;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            ShipFileReader reader = new ShipFileReader();
            String shipsFromFile = reader.readFileAsString(Paths.get("src/main/resources/data/ships.json"));
            ShipParser parser = new ShipParser();
            List<Ship> ships = parser.parse(shipsFromFile);

            Port port = Port.getInstance();
            for (Ship ship: ships) {
                port.addShipToQueue(ship);
            }

            port.startProcessing();
        } catch (ShipException e) {
            logger.log(Level.ERROR, "ShipException in main", e);
        }

    }
}
