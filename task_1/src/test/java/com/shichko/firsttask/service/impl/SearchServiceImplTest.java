package com.shichko.firsttask.service.impl;

import com.shichko.firsttask.entity.IntArray;
import com.shichko.firsttask.service.SearchService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

import static org.testng.Assert.assertEquals;

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