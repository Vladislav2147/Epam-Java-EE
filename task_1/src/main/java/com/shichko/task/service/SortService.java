package com.shichko.task.service;

import com.shichko.task.entity.IntArray;
import com.shichko.task.exception.ArrayException;

public interface SortService {
    IntArray bubbleSort(IntArray array) throws ArrayException;

    IntArray insertionSort(IntArray array) throws ArrayException;

    IntArray selectionSort(IntArray array) throws ArrayException;
}
