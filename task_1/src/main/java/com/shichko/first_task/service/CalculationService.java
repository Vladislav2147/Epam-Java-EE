package com.shichko.first_task.service;

import com.shichko.first_task.entity.IntArray;
import com.shichko.first_task.exception.ArrayException;

import java.util.function.Predicate;

public interface CalculationService {
    void replaceByCondition(IntArray array, int replaced, Predicate<Integer> condition) throws ArrayException;

    int sum(IntArray array);

    double getAverage(IntArray array) throws ArrayException;

    int countPositive(IntArray array);

    int countNegative(IntArray array);
}
