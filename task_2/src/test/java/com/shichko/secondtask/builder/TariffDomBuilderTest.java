package com.shichko.secondtask.builder;

import com.shichko.secondtask.entity.CallPrice;
import com.shichko.secondtask.entity.CallPrices;
import com.shichko.secondtask.exception.TariffXmlException;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TariffDomBuilderTest {

    private final TariffDomBuilder builder = new TariffDomBuilder();
    private final String validFile = "src/test/resources/data/valid_tariffs.xml";
    private final String invalidFile = "src/test/resources/data/invalid_tariffs.xml";

    @Test
    public void testBuildSetTariffsFromValidFile() throws TariffXmlException {
        int expectedSize = 16;

        builder.buildSetTariffs(validFile);
        int actualSize = builder.getTariffs().size();

        assertEquals(actualSize, expectedSize);
    }

    @Test(expectedExceptions = TariffXmlException.class)
    public void testBuildSetTariffsFromInvalidFileThrowsTariffXmlException() throws TariffXmlException {
        builder.buildSetTariffs(invalidFile);
    }
    @Test
    public void testDifferentAttributeOrder() throws TariffXmlException {
        String tariffId = "t0002";
        CallPrices expectedCallPrices = new CallPrices();
        expectedCallPrices.getNetworkCallPrice().setFirstMonthPrice(0.0);
        expectedCallPrices.getNetworkCallPrice().setPrice(0.3);
        expectedCallPrices.getCallPrice().setPrice(0.5);
        expectedCallPrices.getLandlineCallPrice().setFirstMonthPrice(0.50);
        expectedCallPrices.getLandlineCallPrice().setPrice(0.70);

        builder.buildSetTariffs(validFile);
        CallPrices actualCallPrices = builder
                .getTariffs()
                .stream()
                .filter(tariff -> tariff.getId().equals(tariffId))
                .findFirst()
                .get()
                .getCallPrices();

        assertEquals(actualCallPrices, expectedCallPrices);

    }
}