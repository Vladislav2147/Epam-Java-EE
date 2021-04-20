package com.shichko.secondtask.builder;

import com.shichko.secondtask.handler.TariffSaxHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class TariffSaxBuilder extends AbstractTariffBuilder {
    private TariffSaxHandler handler;
    private XMLReader reader;

    public TariffSaxBuilder() {
        super();
        handler = new TariffSaxHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
            reader.setContentHandler(handler);
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buildSetTariffs(String filename) {
        try {
            reader.parse(filename);
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
        tariffs = handler.getTariffs();
    }
}
