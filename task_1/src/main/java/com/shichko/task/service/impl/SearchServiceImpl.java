package com.shichko.task.service.impl;

import com.shichko.task.entity.IntArray;
import com.shichko.task.service.SearchService;
import com.shichko.task.validator.IntArrayValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class SearchServiceImpl implements SearchService {

    private static Logger logger = LogManager.getLogger();

    @Override
    public Optional<Integer> findMin(IntArray array) {
        if (IntArrayValidator.isEmpty(array)) {
            return Optional.empty();
        }
        int min = Integer.MAX_VALUE;
        for (int item: array) {
            if (item < min) {
                min = item;
            }
        }
        Optional<Integer> optionalMin = Optional.of(min);
        logger.log(Level.INFO, "min value is: " + optionalMin);
        return optionalMin;
    }

    @Override
    public Optional<Integer> findMax(IntArray array) {
        if (IntArrayValidator.isEmpty(array)) {
            return Optional.empty();
        }
        int max = Integer.MIN_VALUE;
        for (int item: array) {
            if (item > max) {
                max = item;
            }
        }
        Optional<Integer> optionalMax = Optional.of(max);
        logger.log(Level.INFO, "max value is: " + optionalMax);
        return optionalMax;
    }
}
