package com.shichko.task.service.impl;

import com.shichko.task.entity.IntArray;
import com.shichko.task.service.SortService;

//TODO implement
public class SortServiceStreamImpl implements SortService {
    @Override
    public void bubbleSort(IntArray array) {
//        int length = array.length();
//        IntStream.range(0, length - 1)
//                .flatMap(i -> IntStream.range(0, length - i - 2))
//                .forEach(j -> {
//                    if (array.get(j) > array.get(j + 1)) {
//                        int temp = array.get(j);
//                        array.set(j, array.get(j + 1));
//                        array.set(j + 1, temp);
//                    }
//                });
    }

    @Override
    public void insertionSort(IntArray array) {

    }

    @Override
    public void selectionSort(IntArray array) {

    }
}
