package com.shichko.firsttask.service.impl;

import com.shichko.firsttask.entity.IntArray;
import com.shichko.firsttask.exception.ArrayException;
import com.shichko.firsttask.service.CalculationService;
import com.shichko.firsttask.validator.IntArrayValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Predicate;

public class CalculationServiceImpl implements CalculationService {

    private static Logger logger = LogManager.getLogger();

    @Override
    public void replaceByCondition(IntArray array, int replaced, Predicate<Integer> condition) throws ArrayException {
        for (int i = 0; i < array.length(); i++) {
            if (condition.test(array.get(i))) {
                array.set(i, replaced);
                logger.log(Level.INFO, "set value: " + replaced + " to index: " + i);
            }
        }
    }

    @Override
    public int sum(IntArray array) {
        int sum = 0;
        for (int item: array) {
            sum += item;
        }
        logger.log(Level.INFO, "sum = " + sum);
        return sum;
    }

    @Override
    public double getAverage(IntArray array) throws ArrayException {
        if (IntArrayValidator.isEmpty(array)) {
            logger.log(Level.ERROR, "Array is empty");
            throw new ArrayException("Array has no elements");
        }
        double average = (double) sum(array) / array.length();
        logger.log(Level.INFO, "average = " + average);
        return average;
    }

    @Override
    public int countPositive(IntArray array) {
        int amount = 0;
        for (int item: array) {
            if (item > 0) {
                amount++;
            }
        }
        logger.log(Level.INFO, "amount of positives: " + amount);
        return amount;
    }

    @Override
    public int countNegative(IntArray array) {
        int amount = 0;
        for (int item: array) {
            if (item < 0) {
                amount++;
            }
        }
        logger.log(Level.INFO, "amount of negatives: " + amount);
        return amount;
    }
}
