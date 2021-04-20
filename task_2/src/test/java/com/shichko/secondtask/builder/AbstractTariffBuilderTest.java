package com.shichko.secondtask.builder;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AbstractTariffBuilderTest {

    @Test
    public void testBuildSetTariffs() {
        AbstractTariffBuilder builder1 = new TariffDomBuilder();
        AbstractTariffBuilder builder2 = new TariffStaxBuilder();
        String filename = "src/test/resources/data/tariffs.xml";
        builder1.buildSetTariffs(filename);
        builder2.buildSetTariffs(filename);

        assertEquals(builder1.getTariffs(), builder2.getTariffs());
    }
}