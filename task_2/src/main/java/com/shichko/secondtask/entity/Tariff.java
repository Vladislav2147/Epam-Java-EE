package com.shichko.secondtask.entity;

import com.shichko.secondtask.entity.enums.Operator;

import java.time.LocalDate;

public class Tariff {
    private String id;
    private String name;
    private Operator operator;
    private double payroll;
    private double smsPrice;
    private LocalDate launchDate;
    private CallPrices callPrices = new CallPrices();
    private Params params = new Params();

    public Tariff() {
    }

    public Tariff(String id, String name, Operator operator, double payroll, double smsPrice, LocalDate launchDate, CallPrices callPrices, Params params) {
        this.id = id;
        this.name = name;
        this.operator = operator;
        this.payroll = payroll;
        this.smsPrice = smsPrice;
        this.launchDate = launchDate;
        this.callPrices = callPrices;
        this.params = params;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public double getPayroll() {
        return payroll;
    }

    public void setPayroll(double payroll) {
        this.payroll = payroll;
    }

    public double getSmsPrice() {
        return smsPrice;
    }

    public void setSmsPrice(double smsPrice) {
        this.smsPrice = smsPrice;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }

    public CallPrices getCallPrices() {
        return callPrices;
    }

    public void setCallPrices(CallPrices callPrices) {
        this.callPrices = callPrices;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tariff tariff = (Tariff) o;

        if (Double.compare(tariff.payroll, payroll) != 0) return false;
        if (Double.compare(tariff.smsPrice, smsPrice) != 0) return false;
        if (!id.equals(tariff.id)) return false;
        if (!name.equals(tariff.name)) return false;
        if (operator != tariff.operator) return false;
        if (!launchDate.equals(tariff.launchDate)) return false;
        if (!callPrices.equals(tariff.callPrices)) return false;
        return params.equals(tariff.params);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + operator.hashCode();
        temp = Double.doubleToLongBits(payroll);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(smsPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + launchDate.hashCode();
        result = 31 * result + callPrices.hashCode();
        result = 31 * result + params.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tariff{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", operator=").append(operator);
        sb.append(", payroll=").append(payroll);
        sb.append(", smsPrice=").append(smsPrice);
        sb.append(", launchDate=").append(launchDate);
        sb.append(", callPrices=").append(callPrices);
        sb.append(", params=").append(params);
        sb.append('}');
        return sb.toString();
    }
}
