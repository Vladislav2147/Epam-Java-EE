package com.shichko.task.service.impl;

import com.shichko.task.entity.IntArray;
import com.shichko.task.service.SearchService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.OptionalInt;

import static org.testng.Assert.assertEquals;

public class SearchServiceStreamImplTest {

    private SearchService searchService = new SearchServiceStreamImpl();
    private IntArray intArray;

    @BeforeMethod
    public void init() {
        intArray = new IntArray(new int[] {1, 31, 24, -30});
    }

    @Test
    public void testFindMin() {

        OptionalInt actual = searchService.findMin(intArray);
        OptionalInt expected = Arrays.stream(intArray.getElements()).min();

        assertEquals(actual, expected);
    }

    @Test
    public void testFindMax() {

        OptionalInt actual = searchService.findMax(intArray);
        OptionalInt expected = Arrays.stream(intArray.getElements()).max();

        assertEquals(actual, expected);
    }
}