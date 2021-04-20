package com.shichko.secondtask.validator;

import com.shichko.secondtask.handler.TariffErrorHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlTariffValidator {
    private final static Logger logger = LogManager.getLogger();

    private final static String schemaPath = "src/main/resources/schema/tariffs.xsd";

    private XmlTariffValidator(){ }

    public static boolean validateXml(String xmlFilepath){
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);

        File schemaFile = new File(schemaPath);
        try {
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlFilepath);
            validator.setErrorHandler(new TariffErrorHandler());
            validator.validate(source);
            return true;
        } catch (SAXException | IOException e) {
            logger.log(Level.INFO, "File" + xmlFilepath +  " is not valid.", e);
        }
        return false;
    }
}
