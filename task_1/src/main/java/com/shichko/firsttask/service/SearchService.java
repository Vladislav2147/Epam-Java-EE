package com.shichko.firsttask.service;

import com.shichko.firsttask.entity.IntArray;

import java.util.Optional;

public interface SearchService {

    Optional<Integer> findMin(IntArray array);

    Optional<Integer> findMax(IntArray array);
}
