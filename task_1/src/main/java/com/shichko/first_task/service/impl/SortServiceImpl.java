package com.shichko.first_task.service.impl;

import com.shichko.first_task.entity.IntArray;
import com.shichko.first_task.exception.ArrayException;
import com.shichko.first_task.service.SortService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SortServiceImpl implements SortService {

    private static Logger logger = LogManager.getLogger();

    @Override
    public IntArray bubbleSort(IntArray array) throws ArrayException {
        for (int i = 0; i < array.length(); i++) {
            for (int j = 0; j < array.length() - 1 - i; j++) {
                if (array.get(j) > array.get(j + 1)) {
                    int temp = array.get(j);
                    array.set(j, array.get(j + 1));
                    array.set(j + 1, temp);
                }
            }
        }
        logger.log(Level.INFO, "sorted array: " + array);
        return array;
    }

    @Override
    public IntArray insertionSort(IntArray array) throws ArrayException {
        for (int i = 1; i < array.length(); i++)
        {
            int valueToSort = array.get(i);
            int j;
            for (j = i; j > 0 && array.get(j - 1) > valueToSort; j--) {
                array.set(j, array.get(j - 1));
            }
            array.set(j, valueToSort);
        }
        logger.log(Level.INFO, "sorted array: " + array);
        return array;
    }

    @Override
    public IntArray selectionSort(IntArray array) throws ArrayException {
        for (int i = 0; i < array.length() - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < array.length(); j++)
                if (array.get(j) < array.get(index))
                    index = j;

            int smallerNumber = array.get(index);
            array.set(index, array.get(i));
            array.set(i, smallerNumber);
        }
        logger.log(Level.INFO, "sorted array: " + array);
        return array;
    }

}
