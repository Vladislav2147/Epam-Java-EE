package com.shichko.secondtask.builder;

import com.shichko.secondtask.exception.TariffXmlException;
import com.shichko.secondtask.handler.TariffErrorHandler;
import com.shichko.secondtask.handler.TariffSaxHandler;
import com.shichko.secondtask.validator.XmlTariffValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class TariffSaxBuilder extends AbstractTariffBuilder {

    private static final Logger logger = LogManager.getLogger();

    private final TariffSaxHandler handler;
    private XMLReader reader;

    public TariffSaxBuilder() {
        super();
        handler = new TariffSaxHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
            reader.setContentHandler(handler);
            reader.setErrorHandler(new TariffErrorHandler());
        } catch (ParserConfigurationException | SAXException e) {
            logger.log(Level.ERROR, e);
        }
    }

    @Override
    public void buildSetTariffs(String filename) throws TariffXmlException {
        if (!XmlTariffValidator.validateXml(filename)) {
            throw new TariffXmlException("Document not valid");
        }
        try {
            reader.parse(filename);
        } catch (IOException e) {
            throw new TariffXmlException("Error with file " + filename, e);
        } catch (SAXException e) {
            throw new TariffXmlException( "Error with parsing", e);
        }
        tariffs = handler.getTariffs();
        logger.log(Level.INFO, "tariffs was set from handler. Current size: " + tariffs.size());
    }
}
