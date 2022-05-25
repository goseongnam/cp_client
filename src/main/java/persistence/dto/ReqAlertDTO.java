package persistence.dto;

import java.io.Serializable;

public class ReqAlertDTO implements Serializable {
    private String currencytmp;
    private String alertAmount;
    private int alertNum;

    public ReqAlertDTO(String currencytmp, String alertAmount, int alertNum) {
        this.currencytmp = currencytmp;
        this.alertAmount = alertAmount;
        this.alertNum = alertNum;
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

    public int getAlertNum() {
        return alertNum;
    }

    public void setAlertNum(int alertNum) {
        this.alertNum = alertNum;
    }

}