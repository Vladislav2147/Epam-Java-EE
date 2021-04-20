package com.shichko.secondtask.builder;

import com.google.common.base.CaseFormat;
import com.shichko.secondtask.entity.*;
import com.shichko.secondtask.entity.enums.Operator;
import com.shichko.secondtask.entity.enums.TariffXmlTag;
import com.shichko.secondtask.entity.enums.Tariffication;
import com.shichko.secondtask.exception.TariffXmlException;
import com.shichko.secondtask.validator.XmlTariffValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;

public class TariffStaxBuilder extends AbstractTariffBuilder {

    private static final Logger logger = LogManager.getLogger();

    private XMLInputFactory inputFactory;

    public TariffStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildSetTariffs(String filename) throws TariffXmlException {
        if (!XmlTariffValidator.validateXml(filename)) {
            throw new TariffXmlException("Document not valid");
        }
        try (FileInputStream fileInputStream = new FileInputStream(new File(filename))) {
            XMLStreamReader reader = inputFactory.createXMLStreamReader(fileInputStream);
            String name;

            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(TariffXmlTag.TARIFF.getValue()) || name.equals(TariffXmlTag.ROAMING_TARIFF.getValue())) {
                        Tariff tariff = buildTariff(reader);
                        tariffs.add(tariff);
                        logger.log(Level.INFO, "tariff " + tariff.getId() + " was added to result set");
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new TariffXmlException("Error with stream parsing.", e);
        } catch (IOException e) {
            throw new TariffXmlException("Error with opening file " + filename, e);
        }
        logger.log(Level.INFO, "tariffs was set from handler. Current size: " + tariffs.size());
    }

    private Tariff buildTariff(XMLStreamReader reader) throws XMLStreamException {
        Tariff tariff;
        if (reader.getLocalName().equals(TariffXmlTag.TARIFF.getValue())) {
            tariff = new Tariff();
        } else {
            tariff = new RoamingTariff();
        }
        tariff.setId(reader.getAttributeValue(null, TariffXmlTag.ID.getValue()));

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (TariffXmlTag.valueOf(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, name))) {
                        case NAME:
                            tariff.setName(getXMLText(reader));
                            break;
                        case OPERATOR_NAME:
                            tariff.setOperator(Operator.valueOf(getXMLText(reader)));
                            break;
                        case PAYROLL:
                            tariff.setPayroll(Double.parseDouble(getXMLText(reader)));
                            break;
                        case SMS_PRICE:
                            tariff.setSmsPrice(Double.parseDouble(getXMLText(reader)));
                            break;
                        case LAUNCH_DATE:
                            tariff.setLaunchDate(LocalDate.parse(getXMLText(reader)));
                            break;
                        case ROAMING_PRICE:
                            ((RoamingTariff)tariff).setRoamingPrice(Double.parseDouble(getXMLText(reader)));
                            break;
                        case CALL_PRICES:
                            tariff.setCallPrices(getXMLCallPrices(reader));
                            break;
                        case PARAMS:
                            tariff.setParams(getXMLParams(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    TariffXmlTag endTag = TariffXmlTag.valueOf(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, name));
                    if (endTag == TariffXmlTag.TARIFF || endTag == TariffXmlTag.ROAMING_TARIFF) {
                        return tariff;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <tariff>");
    }

    private CallPrices getXMLCallPrices(XMLStreamReader reader)
            throws XMLStreamException {
        CallPrices callPrices = new CallPrices();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (TariffXmlTag.valueOf(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, name))) {
                        case NETWORK_CALL_PRICE:
                            callPrices.setNetworkCallPrice(getCallPrice(reader));
                            break;
                        case CALL_PRICE:
                            callPrices.setCallPrice(getCallPrice(reader));
                            break;
                        case LANDLINE_CALL_PRICE:
                            callPrices.setLandlineCallPrice(getCallPrice(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    TariffXmlTag endTag = TariffXmlTag.valueOf(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, name));
                    if (endTag == TariffXmlTag.CALL_PRICES) {
                        return callPrices;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <callPrices>");
    }

    private CallPrice getCallPrice(XMLStreamReader reader) {
        CallPrice callPrice = new CallPrice();
        String price = reader.getAttributeValue(null, TariffXmlTag.PRICE.getValue());
        if (price != null) {
            callPrice.setPrice(Double.parseDouble(price));
        }

        String firstMonthPrice = reader.getAttributeValue(null, TariffXmlTag.FIRST_MONTH_PRICE.getValue());
        if (firstMonthPrice != null) {
            callPrice.setFirstMonthPrice(Double.parseDouble(firstMonthPrice));
        }
        return callPrice;
    }

    private Params getXMLParams(XMLStreamReader reader) throws XMLStreamException {
        Params params = new Params();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (TariffXmlTag.valueOf(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, name))) {
                        case FAVORITE_NUMBERS:
                            int favoriteNumbers = Integer.parseInt(getXMLText(reader));
                            params.setFavoriteNumbers(favoriteNumbers);
                            break;
                        case TARIFFICATION:
                            Tariffication tariffication = Tariffication.findByValue(getXMLText(reader));
                            params.setTariffication(tariffication);
                            break;
                        case SUBSCRIBE_PRICE:
                            double subscribePrice = Double.parseDouble(getXMLText(reader));
                            params.setSubscribePrice(subscribePrice);
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    TariffXmlTag endTag = TariffXmlTag.valueOf(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, name));
                    if (endTag == TariffXmlTag.PARAMS) {
                        return params;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <params>");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
