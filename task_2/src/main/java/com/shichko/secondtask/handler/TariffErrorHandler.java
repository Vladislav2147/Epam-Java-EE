package com.shichko.secondtask.handler;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class TariffErrorHandler implements ErrorHandler {
    private final static Logger logger = LogManager.getLogger();

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        logger.log(Level.WARN, getErrorLocation(exception) + "-" + exception.getMessage());
        throw new SAXException(exception);
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        logger.log(Level.ERROR,getErrorLocation(exception) + " - " + exception.getMessage());
        throw new SAXException(exception);
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        logger.log(Level.FATAL,getErrorLocation(exception) + " - " + exception.getMessage());
        throw new SAXException(exception);
    }
    private String getErrorLocation(SAXParseException exception) {
        return "Line: " + exception.getLineNumber() + ", Column: " + exception.getColumnNumber();
    }
}
