package com.shichko.task.service.impl;

import com.shichko.task.entity.IntArray;
import com.shichko.task.service.SearchService;
import com.shichko.task.validator.IntArrayValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.OptionalInt;

public class SearchServiceStreamImpl implements SearchService {

    private static Logger logger = LogManager.getLogger();

    @Override
    public OptionalInt findMin(IntArray array) {
        OptionalInt min = OptionalInt.empty();
        if (IntArrayValidator.isNotEmpty(array)) {
            min = Arrays.stream(array.getElements()).min();
        }
        logger.log(Level.INFO, "min value is: " + min);
        return min;
    }

    @Override
    public OptionalInt findMax(IntArray array) {
        OptionalInt max = OptionalInt.empty();
        if (IntArrayValidator.isNotEmpty(array)) {
            max = Arrays.stream(array.getElements()).max();
        }
        logger.log(Level.INFO, "max value is: " + max);
        return max;
    }
}
