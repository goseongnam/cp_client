package com.example.javafx_practice.item;


import javax.swing.*;

public class AlertTimerObject {
    public String currencytmp;
    public String alertAmount;
    public Timer alertTimer;

    public AlertTimerObject(String currencytmp, String alertAmount, Timer alertTimer) {
        this.currencytmp = currencytmp;
        this.alertAmount = alertAmount;
        this.alertTimer = alertTimer;
    }
}
