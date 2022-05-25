package com.example.javafx_practice;

import com.example.javafx_practice.item.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public ListView lstNationalChange;
    public ListView lstNationalRate;

    //즐겨찾기 버튼
    public Button btnBookmarkAdd;
    //즐겨찾기 버튼5개
    public Button btnBookmark1;
    public Button btnBookmark2;
    public Button btnBookmark3;
    public Button btnBookmark4;
    public Button btnBookmark5;
    //선택된 통화 임시저장 변수
    public String currencytmp = null;
    public Button btnBookmarkDelete;

    public String selectedTmp = null;
    //Alert클릭 시 alertscene에 추가
    public TextField txtAlertInput;
    public String alertAmount = null;
    // 환율변환 관련 텍스트필드
    public TextField txtExInput;
    public TextField txtExOutput;


    Bookmark bookmark;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void AlertListPageMove(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AlertList.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WindowSize.ALERTLIST_X, WindowSize.ALERTLIST_Y);
        StageStore.stage.setTitle("AlertList");
        StageStore.stage.setScene(scene);
        StageStore.stage.show();
    }

    public void CalculatorPageMove(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Calculator.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WindowSize.CALCULATOR_X, WindowSize.CALCULATOR_Y);
        StageStore.stage.setTitle("Calculator");
        StageStore.stage.setScene(scene);
        StageStore.stage.show();
    }

    public void BookmarkPageMove(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Bookmark.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WindowSize.BOOKMARK_X, WindowSize.BOOKMART_Y);
        StageStore.stage.setTitle("Bookmark");
        StageStore.stage.setScene(scene);
        StageStore.stage.show();
    }

    public void ChoiceForexMove(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ChoiceForex.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WindowSize.CHOICEFOREX_X, WindowSize.CHOICEFOREX_Y);
        StageStore.stage.setTitle("ChoiceForex");
        StageStore.stage.setScene(scene);
        StageStore.stage.show();
    }

    public void AlertAddPageMove(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddAlert.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WindowSize.CHOICEALERT_X, WindowSize.CHOICEALERT_Y);
        StageStore.stage.setTitle("ChioceAlert");
        StageStore.stage.setScene(scene);
        StageStore.stage.show();
    }
}