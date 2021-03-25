package com.shichko.first_task.service;

import com.shichko.first_task.entity.IntArray;

public class SearchService {

    private IntArray array;

    public SearchService(IntArray array) {
        this.array = array;
    }

    public int findMin() {
        int min = Integer.MAX_VALUE;
        for (int item: array) {
            if (item < min) {
                min = item;
            }
        }
        return min;
    }

    public int findMax() {
        int max = Integer.MIN_VALUE;
        for (int item: array) {
            if (item > max) {
                max = item;
            }
        }
        return max;
    }

}
