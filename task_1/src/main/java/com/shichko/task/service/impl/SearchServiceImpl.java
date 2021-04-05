package com.shichko.task.service.impl;

import com.shichko.task.entity.IntArray;
import com.shichko.task.service.SearchService;
import com.shichko.task.validator.IntArrayValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.OptionalInt;

public class SearchServiceImpl implements SearchService {

    private static Logger logger = LogManager.getLogger();

    @Override
    public OptionalInt findMin(IntArray array) {
        OptionalInt optionalMin = OptionalInt.empty();
        if (!IntArrayValidator.isNullOrEmpty(array)) {
            int min = Integer.MAX_VALUE;
            for (int item: array) {
                if (item < min) {
                    min = item;
                }
            }
            optionalMin = OptionalInt.of(min);
        }

        logger.log(Level.INFO, "min value is: " + optionalMin);
        return optionalMin;
    }

    @Override
    public OptionalInt findMax(IntArray array) {
        OptionalInt optionalMax = OptionalInt.empty();
        if (!IntArrayValidator.isNullOrEmpty(array)) {
            int max = Integer.MIN_VALUE;
            for (int item: array) {
                if (item > max) {
                    max = item;
                }
            }
            optionalMax = OptionalInt.of(max);
        }

        logger.log(Level.INFO, "max value is: " + optionalMax);
        return optionalMax;
    }
}
