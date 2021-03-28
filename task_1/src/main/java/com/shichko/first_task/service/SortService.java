package com.shichko.first_task.service;

import com.shichko.first_task.entity.IntArray;
import com.shichko.first_task.exception.ArrayException;

public interface SortService {
    IntArray bubbleSort(IntArray array) throws ArrayException;

    IntArray insertionSort(IntArray array) throws ArrayException;

    IntArray selectionSort(IntArray array) throws ArrayException;
}
