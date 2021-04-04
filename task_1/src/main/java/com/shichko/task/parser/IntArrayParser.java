package com.shichko.task.parser;

import com.shichko.task.entity.IntArray;
import com.shichko.task.validator.IntArrayValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class IntArrayParser {

    private static final Logger logger = LogManager.getLogger();

    public Optional<IntArray> parse(List<String> lines) {
        Optional<IntArray> result = Optional.empty();
        if (lines != null) {
            for (String line: lines) {
                if (IntArrayValidator.isValid(line)) {
                    IntArray intArray = parseLine(line);
                    logger.log(Level.INFO, "Valid IntArray to return: " + intArray);
                    result = Optional.of(intArray);
                    break;
                } else {
                    logger.log(Level.WARN, "Invalid line: " + line);
                }
            }
        }
        return result;
    }

    public IntArray parseLine(String line) {
        int[] array = Arrays
                .stream(line.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        IntArray intArray = new IntArray(array);
        return intArray;
    }
}
