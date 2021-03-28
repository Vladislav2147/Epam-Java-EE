package com.shichko.first_task.service;

import com.shichko.first_task.entity.IntArray;

import java.util.Optional;

public interface SearchService {

    Optional<Integer> findMin(IntArray array);

    Optional<Integer> findMax(IntArray array);
}
