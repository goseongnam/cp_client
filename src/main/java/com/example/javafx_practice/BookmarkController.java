package com.example.javafx_practice;

import com.example.javafx_practice.item.Bookmark;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookmarkController implements Initializable {
    @FXML
    public ListView lstNationalChange;

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


    Bookmark bookmark;

    String[] currencyArrayList = {"AED 아랍에미리트 디르함", "AUD 호주 달러", "BHD 바레인 디나르", "BND 브루나이 달러", "CAD 캐나다 달러", "CHF 스위스 프랑",
            "CNH 위안화", "DKK 덴마아크 크로네", "EUR 유로", "GBP 영국 파운드", "HKD 홍콩 달러", "IDR 인도네시아 루피아", "JPY 일본 옌", "KWD 쿠웨이트 디나르",
            "MYR 말레이지아 링기트", "NOK 노르웨이 크로네", "NZD 뉴질랜드 달러", "SAR 사우디 리얄", "SEK 스웨덴 크로나", "SGD 싱가포르 달러", "THB 태국 바트", "USD 미국 달러"};
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Button[] btnBookmarkArr= new Button[5];
        btnBookmarkArr[0]=btnBookmark1;
        btnBookmarkArr[1]=btnBookmark2;
        btnBookmarkArr[2]=btnBookmark3;
        btnBookmarkArr[3]=btnBookmark4;
        btnBookmarkArr[4]=btnBookmark5;
        bookmark = new Bookmark(btnBookmarkArr);

        String arrForex[] = new String[5];
        File file = new File("C:\\fxfile\\bookmark.txt");
        String path = "C:\\fxfile"; //폴더 경로
        try {
            bookmark.readFile(file,path,arrForex);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bookmark.loadBookmark(arrForex,btnBookmarkArr);

        lstNationalChange.setItems(FXCollections.observableArrayList(currencyArrayList));
        lstNationalChange.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String strItem = (String) lstNationalChange.getSelectionModel().getSelectedItem();
                currencytmp = strItem;
            }
        });

    }

    public void moveMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WindowSize.MAIN_X, WindowSize.MAIN_Y);
        StageStore.stage.setTitle("Main");
        StageStore.stage.setScene(scene);
        StageStore.stage.show();
    }
    public void btnBookmark(ActionEvent actionEvent) throws IOException {
        bookmark.btnBookmarkAdd(currencytmp);
    }

    public void btnBookmarkDelete(ActionEvent actionEvent) throws IOException {
        bookmark.btnBookmarkDelete(currencytmp);
    }

    public void moveBookmark(ActionEvent actionEvent) throws IOException {
        bookmark.moveToBookmarkForex(actionEvent);
    }
}
