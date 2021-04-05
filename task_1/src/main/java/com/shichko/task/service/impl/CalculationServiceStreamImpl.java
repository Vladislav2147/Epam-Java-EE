package com.shichko.task.service.impl;

import com.shichko.task.entity.IntArray;
import com.shichko.task.exception.ArrayException;
import com.shichko.task.service.CalculationService;
import com.shichko.task.validator.IntArrayValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.function.Predicate;

public class CalculationServiceStreamImpl implements CalculationService {

    private static Logger logger = LogManager.getLogger();

    @Override
    public void replaceByCondition(IntArray array, int replaced, Predicate<Integer> condition) throws ArrayException {
        if (IntArrayValidator.isNull(array)) {
            throw new ArrayException("IntArray is null");
        }

        int[] elements = Arrays
                .stream(array.getElements())
                .map(operand -> {
                    if (condition.test(operand)) {
                        logger.log(Level.INFO, "set value: " + replaced + " instead of: " + operand);
                        return replaced;
                    } else {
                        return operand;
                    }
                })
                .toArray();
        array.setElements(elements);
    }

    @Override
    public int sum(IntArray array) throws ArrayException {
        if (IntArrayValidator.isNull(array)) {
            throw new ArrayException("IntArray is null");
        }

        int sum = Arrays.stream(array.getElements()).sum();
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

        double average = Arrays
                .stream(array.getElements())
                .average()
                .orElseThrow(() -> {
                    logger.log(Level.ERROR, "Array is empty");
                    return new ArrayException("Array has no elements");
                });
        logger.log(Level.INFO, "average = " + average);
        return average;
    }

    @Override
    public long countPositive(IntArray array) throws ArrayException {
        if (IntArrayValidator.isNull(array)) {
            throw new ArrayException("IntArray is null");
        }

        long positives = Arrays.stream(array.getElements())
                .filter(value -> value > 0)
                .count();
        logger.log(Level.INFO, "amount of positives: " + positives);
        return positives;
    }

    @Override
    public long countNegative(IntArray array) throws ArrayException {
        if (IntArrayValidator.isNull(array)) {
            throw new ArrayException("IntArray is null");
        }

        long negatives = Arrays.stream(array.getElements())
                .filter(value -> value < 0)
                .count();
        logger.log(Level.INFO, "amount of negatives: " + negatives);
        return negatives;
    }
}
