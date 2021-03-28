package com.shichko.first_task.service.impl;

import com.shichko.first_task.entity.IntArray;
import com.shichko.first_task.service.SearchService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.OptionalInt;

import static org.testng.Assert.*;

public class SearchServiceImplTest {

    private SearchService searchService = new SearchServiceImpl();
    private IntArray intArray;

    @BeforeMethod
    public void init() {
        intArray = new IntArray(new int[] {1, 31, 24, -30});
    }

    @Test
    public void testFindMin() {
        Optional<Integer> actual = searchService.findMin(intArray);
        Optional<Integer> expected = Arrays.stream(intArray.getElements()).boxed().min(Comparator.comparingInt(o -> o));
        assertEquals(actual, expected);
    }

    @Test
    public void testFindMax() {
        Optional<Integer> actual = searchService.findMax(intArray);
        Optional<Integer> expected = Arrays.stream(intArray.getElements()).boxed().max(Comparator.comparingInt(o -> o));
        assertEquals(actual, expected);
    }
}