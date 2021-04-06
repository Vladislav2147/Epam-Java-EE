package com.shichko.task.service.impl;

import com.shichko.task.entity.IntArray;
import com.shichko.task.exception.ArrayException;
import com.shichko.task.service.CalculationService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.function.Predicate;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CalculationServiceImplTest {

    private CalculationService calculationService = new CalculationServiceImpl();
    private IntArray intArray;

    @BeforeMethod
    public void init() {
        intArray = new IntArray(new int[] {1, 4, 7, -5, 12, 31});
    }

    @Test
    public void testReplaceByCondition() throws ArrayException {

        Predicate<Integer> condition = item -> item % 2 == 0;
        calculationService.replaceByCondition(intArray, 1, condition);

        assertTrue(Arrays.stream(intArray.getElements()).boxed().noneMatch(condition));
    }

    @Test
    public void testSum() throws ArrayException {

        int actual = calculationService.sum(intArray);
        int expected = Arrays.stream(intArray.getElements()).sum();

        assertEquals(actual, expected);
    }

    @Test
    public void testGetAverage() throws ArrayException {

        double actual = calculationService.getAverage(intArray);
        double expected = (double)Arrays.stream(intArray.getElements()).sum() / intArray.length();

        assertEquals(actual, expected, .00001);
    }

    @Test(expectedExceptions = ArrayException.class)
    public void testGetAverageThrowsOnEmptyArray() throws ArrayException {

        IntArray emptyArray = new IntArray(new int[0]);
        calculationService.getAverage(emptyArray);
    }

    @Test
    public void testCountPositive() throws ArrayException {

        long actual = calculationService.countPositive(intArray);
        long expected = Arrays.stream(intArray.getElements()).filter(num -> num > 0).count();

        assertEquals(actual, expected);
    }

    @Test
    public void testCountNegative() throws ArrayException {

        long actual = calculationService.countNegative(intArray);
        long expected = Arrays.stream(intArray.getElements()).filter(num -> num < 0).count();

        assertEquals(actual, expected);
    }
}