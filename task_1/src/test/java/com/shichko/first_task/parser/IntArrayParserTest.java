package com.shichko.first_task.parser;

import com.shichko.first_task.entity.IntArray;
import com.shichko.first_task.exception.ArrayException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.assertEquals;

public class IntArrayParserTest {

    private IntArrayParser parser;
    private List<String> validLines;
    private List<String> invalidLines;
    private List<String> nullData;

    private int[] expectedElements;

    @BeforeTest
    public void init() {
        parser = new IntArrayParser();

        expectedElements = new int[] {1, 2, 3, 4, 5, 6};

        validLines = new ArrayList<>();
        validLines.add("skl;kfmd;");
        validLines.add("1 2 3 4 5 6");
        validLines.add("5 6 7 8");

        invalidLines = new ArrayList<>();
        invalidLines.add("skl;kfmd;");
        invalidLines.add("1 2. 3 4 5 6");
        invalidLines.add("5 6 d7 8");

        nullData = null;
    }

    @Test
    public void testParseValidData() {
        IntArray expected = new IntArray(expectedElements);
        IntArray actual = parser.parse(validLines).get();

        assertEquals(actual, expected);
    }

    @Test
    public void testParseInvalidDataReturnsOptionalEmpty() {
        Optional<IntArray> actual = parser.parse(invalidLines);

        assertEquals(actual, Optional.empty());
    }

    @Test(expectedExceptions = ArrayException.class)
    public void testParseLineInvalidThrowsArrayException() throws ArrayException {
        parser.parseLine(invalidLines.get(0));
    }
}