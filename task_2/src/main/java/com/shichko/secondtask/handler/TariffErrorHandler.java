package com.shichko.secondtask.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class TariffErrorHandler implements ErrorHandler {
    private final static Logger logger = LogManager.getLogger();

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        logger.warn(getErrorLocation(exception) + "-" + exception.getMessage());
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        logger.error(getErrorLocation(exception) + " - " + exception.getMessage());
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        logger.fatal(getErrorLocation(exception) + " - " + exception.getMessage());
    }
    private String getErrorLocation(SAXParseException exception) {
        return "Line: " + exception.getLineNumber() + ", Column: " + exception.getColumnNumber();
    }
}
