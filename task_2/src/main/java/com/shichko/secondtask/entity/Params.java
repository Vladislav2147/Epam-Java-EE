package com.shichko.secondtask.entity;

import com.shichko.secondtask.entity.enums.Tariffication;

public class Params {
    private int favoriteNumbers;
    private Tariffication tariffication;
    private double subscribePrice;

    public Params() {
    }

    public Params(int favoriteNumbers, Tariffication tariffication, double subscribePrice) {
        this.favoriteNumbers = favoriteNumbers;
        this.tariffication = tariffication;
        this.subscribePrice = subscribePrice;
    }

    public int getFavoriteNumbers() {
        return favoriteNumbers;
    }

    public void setFavoriteNumbers(int favoriteNumbers) {
        this.favoriteNumbers = favoriteNumbers;
    }

    public Tariffication getTariffication() {
        return tariffication;
    }

    public void setTariffication(Tariffication tariffication) {
        this.tariffication = tariffication;
    }

    public double getSubscribePrice() {
        return subscribePrice;
    }

    public void setSubscribePrice(double subscribePrice) {
        this.subscribePrice = subscribePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Params params = (Params) o;

        return favoriteNumbers == params.favoriteNumbers
                && subscribePrice == params.subscribePrice
                && tariffication == params.tariffication;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = favoriteNumbers;
        result = 31 * result + tariffication.hashCode();
        temp = Double.doubleToLongBits(subscribePrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Params{");
        sb.append("favoriteNumbers=").append(favoriteNumbers);
        sb.append(", tariffication=").append(tariffication);
        sb.append(", subscribePrice=").append(subscribePrice);
        sb.append('}');
        return sb.toString();
    }
}
