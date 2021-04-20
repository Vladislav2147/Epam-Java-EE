package com.shichko.secondtask.handler;

import com.google.common.base.CaseFormat;
import com.shichko.secondtask.entity.CallPrice;
import com.shichko.secondtask.entity.RoamingTariff;
import com.shichko.secondtask.entity.Tariff;
import com.shichko.secondtask.entity.enums.Operator;
import com.shichko.secondtask.entity.enums.TariffXmlTag;
import com.shichko.secondtask.entity.enums.Tariffication;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class TariffSaxHandler extends DefaultHandler {

    private Set<Tariff> tariffs;
    private Tariff currentTariff;
    private TariffXmlTag currentTag;
    private EnumSet<TariffXmlTag> withText;

    public TariffSaxHandler() {
        tariffs = new HashSet<>();
        withText = EnumSet.range(TariffXmlTag.NAME, TariffXmlTag.ROAMING_PRICE);
    }

    public Set<Tariff> getTariffs() {
        return tariffs;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        TariffXmlTag temp = TariffXmlTag.valueOf(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, qName));
        switch (temp) {
            case TARIFF:
                currentTariff = new Tariff();
                currentTariff.setId(attributes.getValue(TariffXmlTag.ID.getValue()));
                break;
            case ROAMING_TARIFF:
                currentTariff = new RoamingTariff();
                currentTariff.setId(attributes.getValue(TariffXmlTag.ID.getValue()));
                break;
            case NETWORK_CALL_PRICE:
                currentTariff.getCallPrices().setNetworkCallPrice(getCallPrice(attributes));
            case CALL_PRICE:
                currentTariff.getCallPrices().setCallPrice(getCallPrice(attributes));
            case LANDLINE_CALL_PRICE:
                currentTariff.getCallPrices().setLandlineCallPrice(getCallPrice(attributes));
            default:
                if (withText.contains(temp)) {
                    currentTag = temp;
                }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals(TariffXmlTag.ROAMING_TARIFF.getValue())
                || qName.equals(TariffXmlTag.TARIFF.getValue())) {
            tariffs.add(currentTariff);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length).strip();
        if (currentTag != null) {
            switch (currentTag) {
                case NAME:
                    currentTariff.setName(data);
                    break;
                case OPERATOR_NAME:
                    currentTariff.setOperator(Operator.valueOf(data));
                    break;
                case PAYROLL:
                    currentTariff.setPayroll(Double.parseDouble(data));
                    break;
                case SMS_PRICE:
                    currentTariff.setSmsPrice(Double.parseDouble(data));
                    break;
                case LAUNCH_DATE:
                    currentTariff.setLaunchDate(LocalDate.parse(data));
                    break;
                case FAVORITE_NUMBERS:
                    currentTariff.getParams().setFavoriteNumbers(Integer.parseInt(data));
                    break;
                case TARIFFICATION:
                    currentTariff.getParams().setTariffication(Tariffication.findByValue(data));
                    break;
                case SUBSCRIBE_PRICE:
                    currentTariff.getParams().setSubscribePrice(Double.parseDouble(data));
                    break;
                case ROAMING_PRICE:
                    ((RoamingTariff)currentTariff).setRoamingPrice(Double.parseDouble(data));
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentTag.getDeclaringClass(), currentTag.name());
            }
        }
        currentTag = null;
    }

    private CallPrice getCallPrice(Attributes attributes) {
        CallPrice callPrice = new CallPrice();
        String price = attributes.getValue(TariffXmlTag.PRICE.getValue());
        if (price != null) {
            callPrice.setPrice(Double.parseDouble(price));
        }

        String firstMonthPrice = attributes.getValue(TariffXmlTag.FIRST_MONTH_PRICE.getValue());
        if (firstMonthPrice != null) {
            callPrice.setFirstMonthPrice(Double.parseDouble(firstMonthPrice));
        }
        return callPrice;
    }
}
