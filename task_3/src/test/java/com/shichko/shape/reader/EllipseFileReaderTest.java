package com.shichko.shape.reader;

import com.shichko.shape.exception.EllipseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class EllipseFileReaderTest {

    private EllipseFileReader fileReader;

    @BeforeTest
    public void initFileReader() {
        fileReader = new EllipseFileReader();
    }

    @Test
    public void testReadLinesFromValidFileReturnsOnlyValidStrings() throws EllipseException {
        int expectedSize = 3;
        String validFile = "src/test/resources/ellipses.txt";

        int actualSize = fileReader.readLinesFromFile(validFile).size();
        assertEquals(actualSize, expectedSize);
    }

    @Test(expectedExceptions = EllipseException.class)
    public void testReadLinesFromInvalidFileNameThrowsEllipseException() throws EllipseException {
        String invalidFile = "src/test/resources/ellipsessss.txt";

        fileReader.readLinesFromFile(invalidFile);
    }
}