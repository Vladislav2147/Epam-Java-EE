package com.shichko.secondtask.entity;

public class CallPrice {
    private double price = 0;
    private Double firstMonthPrice;

    public CallPrice() {
    }

    public CallPrice(Double firstMonthPrice) {
        this.firstMonthPrice = firstMonthPrice;
    }

    public CallPrice(double price, Double firstMonthPrice) {
        this.price = price;
        this.firstMonthPrice = firstMonthPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Double getFirstMonthPrice() {
        return firstMonthPrice;
    }

    public void setFirstMonthPrice(Double firstMonthPrice) {
        this.firstMonthPrice = firstMonthPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CallPrice callPrice = (CallPrice) o;

        if (Double.compare(callPrice.price, price) != 0) return false;
        return firstMonthPrice != null ? firstMonthPrice.equals(callPrice.firstMonthPrice) : callPrice.firstMonthPrice == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(price);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (firstMonthPrice != null ? firstMonthPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CallPrice{");
        sb.append("price=").append(price);
        sb.append(", firstMonthPrice=").append(firstMonthPrice);
        sb.append('}');
        return sb.toString();
    }
}
