package com.shichko.shape.filler;

import com.shichko.shape.exception.EllipseException;
import com.shichko.shape.filler.EllipseRepositoryFiller;
import com.shichko.shape.repository.EllipseRepository;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class EllipseRepositoryFillerTest {

    @Test
    public void testFillFromStringListReturnsRepositoryOnValidList() throws EllipseException {
        List<String> validLines = new ArrayList<>();
        validLines.add("-1 1 1 -1");
        validLines.add("3.5 5 6 2");
        validLines.add("2 0.3 7 -8");

        EllipseRepository repository = EllipseRepositoryFiller.fillFromStringList(validLines);
        assertEquals(repository.size(), validLines.size());
    }

    @Test(expectedExceptions = EllipseException.class)
    public void testFillFromStringListThrowsEllipseExceptionOnInvalidList() throws EllipseException {
        List<String> invalidLines = new ArrayList<>();
        invalidLines.add("some string");
        invalidLines.add("3.5 5 6 2 5");
        invalidLines.add("2 0.3 7 -8");

        EllipseRepositoryFiller.fillFromStringList(invalidLines);
    }
}