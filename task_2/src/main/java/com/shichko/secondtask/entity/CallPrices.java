package com.shichko.secondtask.entity;

public class CallPrices {
    private CallPrice networkCallPrice;
    private CallPrice callPrice;
    private CallPrice landlineCallPrice;

    public CallPrices(CallPrice networkCallPrice, CallPrice callPrice, CallPrice landlineCallPrice) {
        this.networkCallPrice = networkCallPrice;
        this.callPrice = callPrice;
        this.landlineCallPrice = landlineCallPrice;
    }

    public CallPrice getNetworkCallPrice() {
        return networkCallPrice;
    }

    public void setNetworkCallPrice(CallPrice networkCallPrice) {
        this.networkCallPrice = networkCallPrice;
    }

    public CallPrice getCallPrice() {
        return callPrice;
    }

    public void setCallPrice(CallPrice callPrice) {
        this.callPrice = callPrice;
    }

    public CallPrice getLandlineCallPrice() {
        return landlineCallPrice;
    }

    public void setLandlineCallPrice(CallPrice landlineCallPrice) {
        this.landlineCallPrice = landlineCallPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CallPrices that = (CallPrices) o;

        return networkCallPrice.equals(that.networkCallPrice)
                && callPrice.equals(that.callPrice)
                && landlineCallPrice.equals(that.landlineCallPrice);
    }

    @Override
    public int hashCode() {
        int result = networkCallPrice.hashCode();
        result = 31 * result + callPrice.hashCode();
        result = 31 * result + landlineCallPrice.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CallPrices{");
        sb.append("networkCallPrice=").append(networkCallPrice);
        sb.append(", callPrice=").append(callPrice);
        sb.append(", landlineCallPrice=").append(landlineCallPrice);
        sb.append('}');
        return sb.toString();
    }
}
