package com.shichko.secondtask.entity;

import com.shichko.secondtask.entity.enums.Operator;

import java.time.LocalDate;

public class RoamingTariff extends Tariff {
    private double roamingPrice;

    public RoamingTariff() { }

    public RoamingTariff(String id, String name, Operator operator, double payroll, double smsPrice, LocalDate launchDate, CallPrices callPrices, Params params, double roamingPrice) {
        super(id, name, operator, payroll, smsPrice, launchDate, callPrices, params);
        this.roamingPrice = roamingPrice;
    }

    public double getRoamingPrice() {
        return roamingPrice;
    }

    public void setRoamingPrice(double roamingPrice) {
        this.roamingPrice = roamingPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RoamingTariff that = (RoamingTariff) o;

        return that.roamingPrice == roamingPrice;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(roamingPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RoamingTariff{");
        sb.append("roamingPrice=").append(roamingPrice);
        sb.append(super.toString());
        sb.append('}');
        return sb.toString();
    }
}
