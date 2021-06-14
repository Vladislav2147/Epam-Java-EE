package com.shichko.multithreading.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.shichko.multithreading.entity.Ship;
import com.shichko.multithreading.exception.ShipException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ShipParser {
    private static final Logger logger = LogManager.getLogger();

    public List<Ship> parse(String jsonShips) throws ShipException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            CollectionType listOfShipsType = mapper.getTypeFactory().constructCollectionType(List.class, Ship.class);
            List<Ship> ships = mapper.readValue(jsonShips, listOfShipsType);
            logger.log(Level.INFO, "Deserialized list of ships with size: " + ships.size());
            return ships;
        } catch (JsonProcessingException e) {
            logger.log(Level.ERROR, "Failed to parse string: " + jsonShips, e);
            throw new ShipException("Failed to parse string: " + jsonShips, e);
        }
    }
}
