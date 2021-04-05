package com.shichko.task.service;

import com.shichko.task.entity.IntArray;
import com.shichko.task.exception.ArrayException;

public interface SortService {
    void bubbleSort(IntArray array) throws ArrayException;

    void insertionSort(IntArray array) throws ArrayException;

    void selectionSort(IntArray array) throws ArrayException;

    void streamSort(IntArray array) throws ArrayException;
}
