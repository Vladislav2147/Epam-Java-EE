package com.shichko.task.service;

import com.shichko.task.entity.IntArray;

public interface SortService {
    void bubbleSort(IntArray array);

    void insertionSort(IntArray array);

    void selectionSort(IntArray array);

    void streamSort(IntArray array);
}
