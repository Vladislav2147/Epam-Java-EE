package com.shichko.task.service.impl;

import com.shichko.task.entity.IntArray;
import com.shichko.task.exception.ArrayException;
import com.shichko.task.service.CalculationService;
import com.shichko.task.validator.IntArrayValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Predicate;

public class CalculationServiceImpl implements CalculationService {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void replaceByCondition(IntArray array, int replaced, Predicate<Integer> condition) throws ArrayException {
        if (IntArrayValidator.isNull(array)) {
            throw new ArrayException("IntArray is null");
        }
        if (condition == null) {
            throw new ArrayException("condition is null");
        }

        for (int i = 0; i < array.length(); i++) {
            if (condition.test(array.get(i))) {
                array.set(i, replaced);
                logger.log(Level.INFO, "set value: " + replaced + " to index: " + i);
            }
        }
    }

    @Override
    public int sum(IntArray array) throws ArrayException {
        if (IntArrayValidator.isNull(array)) {
            throw new ArrayException("IntArray is null");
        }

        int sum = 0;
        for (int item: array) {
            sum += item;
        }
        logger.log(Level.INFO, "sum = " + sum);
        return sum;
    }

    @Override
    public double getAverage(IntArray array) throws ArrayException {
        if (IntArrayValidator.isNull(array)) {
            throw new ArrayException("IntArray is null");
        }
        if (IntArrayValidator.isEmpty(array)) {
            throw new ArrayException("IntArray is empty");
        }

        double average = (double) sum(array) / array.length();
        logger.log(Level.INFO, "average = " + average);
        return average;
    }

    @Override
    public long countPositive(IntArray array) throws ArrayException {
        if (IntArrayValidator.isNull(array)) {
            throw new ArrayException("IntArray is null");
        }

        long amount = 0;
        for (int item: array) {
            if (item > 0) {
                amount++;
            }
        }
        logger.log(Level.INFO, "amount of positives: " + amount);
        return amount;
    }

    @Override
    public long countNegative(IntArray array) throws ArrayException {
        if (IntArrayValidator.isNull(array)) {
            throw new ArrayException("IntArray is null");
        }

        long amount = 0;
        for (int item: array) {
            if (item < 0) {
                amount++;
            }
        }
        logger.log(Level.INFO, "amount of negatives: " + amount);
        return amount;
    }
}
