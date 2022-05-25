package com.example.javafx_practice;

import com.example.javafx_practice.item.Forex;
import com.example.javafx_practice.item.StageStore;
import com.example.javafx_practice.item.WindowSize;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import persistence.dto.ReqGraphDTO;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class ForexController implements Initializable {

    public TextField txtDateInput;
    public TextArea txtDateOutput;
    public Button btnDateOk;
    public Button btnMonth;
    public Button btn3Month;
    public Button btnYear;
    public TextField txtAverageOutput;
    public TextField txtStandardDeviationOutput;
    public LineChart<Number, Number> chart;
    public NumberAxis yAxis;

    //ObservableList<XYChart.Series<Number, Number>> list;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        txtDateOutput.setDisable(true);
        txtAverageOutput.setDisable(true);
        txtStandardDeviationOutput.setDisable(true);
        txtDateInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtDateInput.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        Forex.setForexSearch(txtDateInput,txtDateOutput);
        Forex.setForexGraph(txtAverageOutput,txtStandardDeviationOutput,chart,yAxis);
    }

    public void searchForex(ActionEvent actionEvent) throws MalformedURLException {//서버로 데이터 전송 및 수신
        Forex.reqSearchForex();
    }

    public void viewGraphMonth(ActionEvent actionEvent) {//서버로 데이터 전송 및 수신
        Forex.reqGraphMonth();
    }

    public void viewGraphYear(ActionEvent actionEvent) {//서버로 데이터 전송 및 수신
        Forex.reqGraphYear();
    }
    public void viewGraph3Month(){Forex.reqGraph3Month();}

    public void moveMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WindowSize.MAIN_X, WindowSize.MAIN_Y);
        StageStore.stage.setTitle("Main");
        StageStore.stage.setScene(scene);
        StageStore.stage.show();
    }
    
}