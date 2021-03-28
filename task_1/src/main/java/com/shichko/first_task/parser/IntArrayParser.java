package com.shichko.first_task.parser;

import com.shichko.first_task.entity.IntArray;
import com.shichko.first_task.exception.ArrayException;
import com.shichko.first_task.validator.IntArrayValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class IntArrayParser {

    private static final Logger logger = LogManager.getLogger();

    public Optional<IntArray> parse(List<String> lines) {
        for (String line: lines) {
            try {
                return Optional.of(parseLine(line));
            } catch (ArrayException e) {
                logger.log(Level.ERROR, "Invalid line", e);
            }
        }
        return Optional.empty();
    }

    private IntArray parseLine(String line) throws ArrayException {
        try {
            int[] array = Arrays
                    .stream(line.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            IntArray intArray = new IntArray(array);

            if (IntArrayValidator.isEmpty(intArray)) {
                throw new ArrayException("Array is empty");
            }
            return intArray;
        } catch (NumberFormatException e) {
            logger.log(Level.ERROR, "Invalid item", e);
            throw new ArrayException("Invalid item", e);
        }
    }
}
