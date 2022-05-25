package com.example.javafx_practice;

import com.example.javafx_practice.item.AlertSet;
import com.example.javafx_practice.item.StageStore;
import com.example.javafx_practice.item.WindowSize;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class AddAlertController implements Initializable {
    @FXML
    public String currencytmp = null;

    public String selectedTmp = null;

    public TextField txtAlertInput;
    public String alertAmount = null;
    public ListView lstNationalChange;


    String[] currencyArrayList = {"AED 아랍에미리트 디르함", "AUD 호주 달러", "BHD 바레인 디나르", "CAD 캐나다 달러", "CHF 스위스 프랑",
            "CNH 위안화", "DKK 덴마아크 크로네", "EUR 유로", "GBP 영국 파운드", "HKD 홍콩 달러", "IDR 인도네시아 루피아", "JPY 일본 옌", "KWD 쿠웨이트 디나르",
            "MYR 말레이지아 링기트", "NOK 노르웨이 크로네", "NZD 뉴질랜드 달러", "SAR 사우디 리얄", "SEK 스웨덴 크로나", "SGD 싱가포르 달러", "THB 태국 바트", "USD 미국 달러"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        lstNationalChange.setItems(FXCollections.observableArrayList(currencyArrayList));
        lstNationalChange.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String strItem = (String) lstNationalChange.getSelectionModel().getSelectedItem();
                currencytmp = strItem;
            }
        });
        txtAlertInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtAlertInput.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        //여기 부분에서 파일에서 읽어와서 arraylist에다가 추가해줘야 함.
    }

    public void alertClick(ActionEvent actionEvent) throws IOException {
        AlertSet.alertClick(txtAlertInput, currencytmp, alertAmount);
    }


    public void moveMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WindowSize.MAIN_X, WindowSize.MAIN_Y);
        StageStore.stage.setTitle("Main");
        StageStore.stage.setScene(scene);
        StageStore.stage.show();
    }

    public void AlertListPageMove(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AlertList.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WindowSize.ALERTLIST_X, WindowSize.ALERTLIST_Y);
        StageStore.stage.setTitle("AlertStack");
        StageStore.stage.setScene(scene);
        StageStore.stage.show();
    }
}