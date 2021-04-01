package com.shichko.task.service;

import com.shichko.task.entity.IntArray;

import java.util.OptionalInt;

public interface SearchService {

    OptionalInt findMin(IntArray array);

    OptionalInt findMax(IntArray array);
}
