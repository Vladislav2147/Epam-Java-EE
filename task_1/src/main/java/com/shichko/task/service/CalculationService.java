package com.shichko.task.service;

import com.shichko.task.entity.IntArray;
import com.shichko.task.exception.ArrayException;

import java.util.function.Predicate;

public interface CalculationService {
    void replaceByCondition(IntArray array, int replaced, Predicate<Integer> condition) throws ArrayException;

    int sum(IntArray array) throws ArrayException;

    double getAverage(IntArray array) throws ArrayException;

    long countPositive(IntArray array) throws ArrayException;

    long countNegative(IntArray array) throws ArrayException;
}
