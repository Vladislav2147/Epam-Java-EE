package com.shichko.secondtask;

import com.shichko.secondtask.builder.AbstractTariffBuilder;
import com.shichko.secondtask.builder.TariffDomBuilder;
import com.shichko.secondtask.builder.TariffStaxBuilder;
import com.shichko.secondtask.exception.TariffXmlException;

public class Main {
    public static void main(String... args) throws TariffXmlException {
        AbstractTariffBuilder builder1 = new TariffDomBuilder();
        AbstractTariffBuilder builder2 = new TariffStaxBuilder();
        String filename = "src/test/resources/data/valid_tariffs.xml";
        builder1.buildSetTariffs(filename);
        builder2.buildSetTariffs(filename);

    }
}
