package com.shichko.firsttask.service;

import com.shichko.firsttask.entity.IntArray;
import com.shichko.firsttask.exception.ArrayException;

public interface SortService {
    IntArray bubbleSort(IntArray array) throws ArrayException;

    IntArray insertionSort(IntArray array) throws ArrayException;

    IntArray selectionSort(IntArray array) throws ArrayException;
}
