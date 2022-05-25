package com.example.javafx_practice.item;

public class ReqAlertObject {
    private String currencytmp;
    private String alertAmount;

    public ReqAlertObject(String currencytmp, String alertAmount) {
        this.currencytmp = currencytmp;
        this.alertAmount = alertAmount;
    }

    public String getCurrencytmp() {
        return currencytmp;
    }

    public void setCurrencytmp(String currencytmp) {
        this.currencytmp = currencytmp;
    }

    public String getAlertAmount() {
        return alertAmount;
    }

    public void setAlertAmount(String alertAmount) {
        this.alertAmount = alertAmount;
    }
}