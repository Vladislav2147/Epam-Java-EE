package com.shichko.secondtask.factory;

import com.shichko.secondtask.builder.AbstractTariffBuilder;
import com.shichko.secondtask.builder.TariffStaxBuilder;
import com.shichko.secondtask.exception.TariffXmlException;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TariffBuilderFactoryTest {

    @Test
    public void testCreateValidTariffStaxBuilder() throws TariffXmlException {
        String validBuilderType = "stax";

        AbstractTariffBuilder builder = TariffBuilderFactory.createTariffBuilder(validBuilderType);

        assertTrue(builder instanceof TariffStaxBuilder);
    }

    @Test(expectedExceptions = TariffXmlException.class)
    public void testCreateInvalidTariffBuilderThrowsTariffXmlException() throws TariffXmlException {
        String invalidBuilderType = "invalidbuildertype";

        AbstractTariffBuilder builder = TariffBuilderFactory.createTariffBuilder(invalidBuilderType);
    }
}