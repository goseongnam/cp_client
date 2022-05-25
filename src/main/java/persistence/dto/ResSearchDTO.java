package persistence.dto;

import java.io.Serializable;

public class ResSearchDTO implements Serializable {
    private static final long serialVersionUID = 2L;
    private String unit;
    private String ttb;
    private String tts;
    private String deal;
    private String bkpr;

    public ResSearchDTO(String date, String unit, String ttb, String tts, String deal, String bkpr) {
        this.unit = unit;
        this.ttb = ttb;
        this.tts = tts;
        this.deal = deal;
        this.bkpr = bkpr;
    }

    public ResSearchDTO(){

    }


    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTtb() {
        return ttb;
    }

    public void setTtb(String ttb) {
        this.ttb = ttb;
    }

    public String getTts() {
        return tts;
    }

    public void setTts(String tts) {
        this.tts = tts;
    }

    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }

    public String getBkpr() {
        return bkpr;
    }

    public void setBkpr(String bkpr) {
        this.bkpr = bkpr;
    }
}