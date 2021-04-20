package com.shichko.secondtask.factory;

import com.shichko.secondtask.builder.AbstractTariffBuilder;
import com.shichko.secondtask.builder.TariffDomBuilder;
import com.shichko.secondtask.builder.TariffSaxBuilder;
import com.shichko.secondtask.builder.TariffStaxBuilder;
import com.shichko.secondtask.exception.TariffXmlException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TariffBuilderFactory {

    private static final Logger logger = LogManager.getLogger();

    private enum ParserType {
        DOM, SAX, STAX
    }

    private TariffBuilderFactory() { }

    public static AbstractTariffBuilder createTariffBuilder(String builderType) throws TariffXmlException {
        try {
            ParserType type = ParserType.valueOf(builderType.toUpperCase());
            AbstractTariffBuilder builder = null;
            switch (type) {
                case DOM:
                    builder = new TariffDomBuilder();
                    break;
                case SAX:
                    builder = new TariffSaxBuilder();
                    break;
                case STAX:
                    builder = new TariffStaxBuilder();
                    break;
            }
            logger.log(Level.INFO, "returning builder " + builder.getClass().getName());
            return builder;
        } catch (IllegalArgumentException e) {
            throw new TariffXmlException("Builder type " + builderType + " not found", e);
        }
    }
}