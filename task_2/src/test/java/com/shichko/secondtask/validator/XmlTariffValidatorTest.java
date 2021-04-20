package com.shichko.secondtask.validator;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class XmlTariffValidatorTest {

    @Test
    public void testValidateValidXml() {
        String validFile = "src/test/resources/data/valid_tariffs.xml";

        boolean isValid = XmlTariffValidator.validateXml(validFile);

        assertTrue(isValid);
    }

    @Test
    public void testValidateInvalidXml() {
        String invalidFile = "src/test/resources/data/invalid_tariffs.xml";

        boolean isValid = XmlTariffValidator.validateXml(invalidFile);

        assertFalse(isValid);
    }
}