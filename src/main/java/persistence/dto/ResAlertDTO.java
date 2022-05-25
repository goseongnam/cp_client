package persistence.dto;

import java.io.Serializable;

public class ResAlertDTO implements Serializable {
    private String currencytmp;
    private String alertAmount;
    private boolean judgement;

    public ResAlertDTO(String currencytmp, String alertAmount, boolean judgement) {
        this.currencytmp = currencytmp;
        this.alertAmount = alertAmount;
        this.judgement = judgement;
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

    public boolean isJudgement() {
        return judgement;
    }

    public void setJudgement(boolean judgement) {
        this.judgement = judgement;
    }
}