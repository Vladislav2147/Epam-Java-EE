package com.shichko.shape.parser;

import com.shichko.shape.exception.EllipseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class EllipseParserTest {

    private EllipseParser parser;

    @BeforeTest
    public void initParser() {
        parser = new EllipseParser();
    }

    @Test
    public void testParseValidDataString() throws EllipseException {
        int expectedLength = 4;
        String validString = "1.0 -0.4 2.6 -10";

        double[] parsedCoords = parser.parse(validString);
        assertEquals(parsedCoords.length, expectedLength);
    }

    @Test(expectedExceptions = EllipseException.class)
    public void testParseInvalidDataStringThrowsEllipseException() throws EllipseException {
        String invalidString = "1.0 --0.4 2.6 -10";

        parser.parse(invalidString);
    }
}