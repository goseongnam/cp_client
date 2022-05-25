package com.example.javafx_practice;

import com.example.javafx_practice.item.Bookmark;
import com.example.javafx_practice.item.Calculation;
import com.example.javafx_practice.item.StageStore;
import com.example.javafx_practice.item.WindowSize;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class CalculatorController implements Initializable {
    @FXML
    public ListView lstNationalChange;

    // 환율변환 관련 텍스트필드
    public TextField txtExInput;
    public TextField txtExOutput;
    public ChoiceBox choiceMethod;
    String[] currencyArrayList = {"AED 아랍에미리트 디르함", "AUD 호주 달러", "BHD 바레인 디나르", "CAD 캐나다 달러", "CHF 스위스 프랑",
            "CNH 위안화", "DKK 덴마아크 크로네", "EUR 유로", "GBP 영국 파운드", "HKD 홍콩 달러", "IDR 인도네시아 루피아", "JPY 일본 옌", "KWD 쿠웨이트 디나르",
            "MYR 말레이지아 링기트", "NOK 노르웨이 크로네", "NZD 뉴질랜드 달러", "SAR 사우디 리얄", "SEK 스웨덴 크로나", "SGD 싱가포르 달러", "THB 태국 바트", "USD 미국 달러"};
    public String currencytmp = null;

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
        txtExInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtExInput.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        txtExOutput.setDisable(true);
        choiceMethod.getItems().add("BKPR (장부가격 기준)");
        choiceMethod.getItems().add("TTB (전신환 매입율 기준)");
        choiceMethod.getItems().add("TTS (전신환매도율 기준)");
        choiceMethod.getItems().add("DEAL (매매율 기준)");

        Calculation.setCalculation(currencytmp, choiceMethod, txtExInput, txtExOutput);
    }
    public void btnChk_Calculate(ActionEvent actionEvent) throws IOException {
        Calculation.btnChk_Calculate();
    }

    public void moveMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WindowSize.MAIN_X, WindowSize.MAIN_Y);
        StageStore.stage.setTitle("Main");
        StageStore.stage.setScene(scene);
        StageStore.stage.show();
    }
}
