package com.shichko.secondtask.entity.enums;

public enum TariffXmlTag {
    //with text
    NAME("name"),
    OPERATOR_NAME("operatorName"),
    PAYROLL("payroll"),
    SMS_PRICE("smsPrice"),
    LAUNCH_DATE("launchDate"),
    FAVORITE_NUMBERS("favoriteNumbers"),
    TARIFFICATION("tariffication"),
    SUBSCRIBE_PRICE("subscribePrice"),
    ROAMING_PRICE("roamingPrice"),

    //without text
    CALL_PRICE("callPrice"),
    CALL_PRICES("callPrices"),
    LANDLINE_CALL_PRICE("landlineCallPrice"),
    NETWORK_CALL_PRICE("networkCallPrice"),
    PARAMS("params"),
    ROAMING_TARIFF("roamingTariff"),
    TARIFF("tariff"),
    TARIFFS("tariffs"),
    ID("id"),
    PRICE("price"),
    FIRST_MONTH_PRICE("firstMonthPrice");

    private final String value;

    TariffXmlTag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
