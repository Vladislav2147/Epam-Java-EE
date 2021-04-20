package com.shichko.secondtask.builder;

import com.shichko.secondtask.entity.CallPrice;
import com.shichko.secondtask.entity.RoamingTariff;
import com.shichko.secondtask.entity.Tariff;
import com.shichko.secondtask.entity.enums.Operator;
import com.shichko.secondtask.entity.enums.TariffXmlTag;
import com.shichko.secondtask.entity.enums.Tariffication;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;

public class TariffDomBuilder extends AbstractTariffBuilder {
    private DocumentBuilder docBuilder;

    public TariffDomBuilder() {
        super();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            //TODO
        }
    }

    @Override
    public void buildSetTariffs(String filename) {
        Document document = null;
        try {
            document = docBuilder.parse(filename);
            Element root = document.getDocumentElement();
            NodeList tariffsList = root.getElementsByTagName(TariffXmlTag.TARIFF.getValue());
            for (int i = 0; i < tariffsList.getLength(); i++) {
                Element tariffElement = (Element) tariffsList.item(i);
                Tariff tariff = buildTariff(tariffElement);
                tariffs.add(tariff);
            }

            NodeList roamingTariffsList = root.getElementsByTagName(TariffXmlTag.ROAMING_TARIFF.getValue());
            for (int i = 0; i < roamingTariffsList.getLength(); i++) {
                Element roamingTariffElement = (Element) roamingTariffsList.item(i);
                Tariff tariff = buildTariff(roamingTariffElement);
                tariffs.add(tariff);
            }

        } catch (IOException | SAXException e) {
            //TODO
        }
    }

    private Tariff buildTariff(Element tariffElement) {
        Tariff tariff;
        if (tariffElement.getTagName().equals(TariffXmlTag.TARIFF.getValue())) {
            tariff = new Tariff();
        } else {
            tariff = new RoamingTariff();

            double roamingPrice = Double.parseDouble(getElementTextContent(tariffElement, TariffXmlTag.ROAMING_PRICE.getValue()));
            ((RoamingTariff) tariff).setRoamingPrice(roamingPrice);
        }

        tariff.setId(tariffElement.getAttribute(TariffXmlTag.ID.getValue()));
        tariff.setName(getElementTextContent(tariffElement, TariffXmlTag.NAME.getValue()));

        Operator operator = Operator.valueOf(getElementTextContent(tariffElement, TariffXmlTag.OPERATOR_NAME.getValue()));
        tariff.setOperator(operator);

        double payroll = Double.parseDouble(getElementTextContent(tariffElement, TariffXmlTag.PAYROLL.getValue()));
        tariff.setPayroll(payroll);

        double smsPrice = Double.parseDouble(getElementTextContent(tariffElement, TariffXmlTag.SMS_PRICE.getValue()));
        tariff.setSmsPrice(smsPrice);

        LocalDate launchDate = LocalDate.parse(getElementTextContent(tariffElement, TariffXmlTag.LAUNCH_DATE.getValue()));
        tariff.setLaunchDate(launchDate);

        Element callPrices = (Element) tariffElement.getElementsByTagName(TariffXmlTag.CALL_PRICES.getValue()).item(0);
        CallPrice landlineCallPrice = getCallPrice((Element) callPrices.getElementsByTagName(TariffXmlTag.LANDLINE_CALL_PRICE.getValue()).item(0));
        tariff.getCallPrices().setLandlineCallPrice(landlineCallPrice);
        CallPrice callPrice = getCallPrice((Element) callPrices.getElementsByTagName(TariffXmlTag.CALL_PRICE.getValue()).item(0));
        tariff.getCallPrices().setCallPrice(callPrice);
        CallPrice networkCallPrice = getCallPrice((Element) callPrices.getElementsByTagName(TariffXmlTag.NETWORK_CALL_PRICE.getValue()).item(0));
        tariff.getCallPrices().setNetworkCallPrice(networkCallPrice);

        Element params = (Element) tariffElement.getElementsByTagName(TariffXmlTag.PARAMS.getValue()).item(0);
        int favoriteNumbers = Integer.parseInt(getElementTextContent(params, TariffXmlTag.FAVORITE_NUMBERS.getValue()));
        tariff.getParams().setFavoriteNumbers(favoriteNumbers);
        Tariffication tariffication = Tariffication.findByValue(getElementTextContent(params, TariffXmlTag.TARIFFICATION.getValue()));
        tariff.getParams().setTariffication(tariffication);
        double subscribePrice = Double.parseDouble(getElementTextContent(params, TariffXmlTag.SUBSCRIBE_PRICE.getValue()));
        tariff.getParams().setSubscribePrice(subscribePrice);

        return tariff;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }

    private static CallPrice getCallPrice(Element element) {
        CallPrice callPrice = new CallPrice();

        String price = element.getAttribute(TariffXmlTag.PRICE.getValue());
        if (!price.isEmpty()) {
            callPrice.setPrice(Double.parseDouble(price));
        }

        String firstMonthPrice = element.getAttribute(TariffXmlTag.FIRST_MONTH_PRICE.getValue());
        if (!firstMonthPrice.isEmpty()) {
            callPrice.setFirstMonthPrice(Double.parseDouble(firstMonthPrice));
        }

        return callPrice;
    }
}
