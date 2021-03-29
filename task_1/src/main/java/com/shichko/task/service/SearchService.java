package com.shichko.task.service;

import com.shichko.task.entity.IntArray;

import java.util.Optional;

public interface SearchService {

    Optional<Integer> findMin(IntArray array);

    Optional<Integer> findMax(IntArray array);
}
