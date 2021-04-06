package com.shichko.task.service.impl;

import com.shichko.task.entity.IntArray;
import com.shichko.task.exception.ArrayException;
import com.shichko.task.service.SortService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

public class SortServiceImplTest {

    private SortService sortService;
    private IntArray intArray;
    private IntArray sortedArray;

    @BeforeTest
    public void init() {
        sortService = new SortServiceImpl();
    }

    @BeforeMethod
    public void initIntArray() {

        int[] elements = new int[]{1, -10, 40, 12, 24, -1000};
        intArray = new IntArray(elements);

        Arrays.sort(elements);
        sortedArray = new IntArray(elements);
    }

    @Test
    public void testBubbleSort() throws ArrayException {

        sortService.bubbleSort(intArray);

        assertEquals(intArray.getElements(), sortedArray.getElements());
    }

    @Test
    public void testInsertionSort() throws ArrayException {

        sortService.insertionSort(intArray);

        assertEquals(intArray.getElements(), sortedArray.getElements());
    }

    @Test
    public void testSelectionSort() throws ArrayException {

        sortService.selectionSort(intArray);

        assertEquals(intArray.getElements(), sortedArray.getElements());
    }

    @Test
    public void testStreamSort() throws ArrayException {

        sortService.streamSort(intArray);

        assertEquals(intArray.getElements(), sortedArray.getElements());
    }

}