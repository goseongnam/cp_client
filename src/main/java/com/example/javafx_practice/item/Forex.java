package com.example.javafx_practice.item;

import com.example.javafx_practice.ForexController;
import com.example.javafx_practice.Protocol;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import persistence.dto.ReqGraphDTO;
import persistence.dto.ReqSearchDTO;
import persistence.dto.ResGraphDTO;
import persistence.dto.ResSearchDTO;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Forex {
    private static TextField txtDateInput;
    private static TextArea txtDateOutput;
    private static TextField txtAverageOutput;
    private static TextField txtStandardDeviationOutput;
    private static LineChart<Number, Number> chart;
    private static String forex;
    private static ObservableList<XYChart.Series<Number, Number>> list;
    private static NumberAxis yAxis;

    public static void setForex(TextField tf, String t){
        txtDateInput = tf;
        forex = t;
    }
    public static void setForexSearch(TextField txtin, TextArea txtout){
        txtDateInput = txtin;
        txtDateOutput = txtout;
    }
    public static void setForexGraph(TextField txtAvg, TextField txtSD,
                                     LineChart<Number, Number> c, NumberAxis y){
        txtAverageOutput = txtAvg;
        txtStandardDeviationOutput = txtSD;
        chart = c;
        yAxis=y;
    }


    public static void reqSearchForex(){
        String dateInput="";

        String title= StageStore.stage.getTitle();
        String forex =title.substring(0,3);
        Forex.setForex(txtDateInput,forex);

        dateInput = txtDateInput.getText();
        if (txtDateInput.getText()!=null){
            if(dateInput.length()!=8){//날짜 입력이 올바른지 체킹
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Look, a Warning Dialog");
                alert.setContentText("날짜는 8자리로 입력해야 합니다.");
                alert.showAndWait();
                return;
            }
        } else {
            return; // null 값이라서 종료함.
        }
        ReqSearchDTO reqSearchDTO = new ReqSearchDTO(dateInput,forex);

        System.out.println(reqSearchDTO.getDateInput());

        try {
            Protocol.requestToServer(Protocol.TYPE_REQ_SEARCH,Protocol.CODE_REQ_SEARCH,reqSearchDTO);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Protocol.receiveData();

    }


    public static void reqGraphMonth(){
        String title= StageStore.stage.getTitle();
        String forex =title.substring(0,3);
        Forex.setForex(txtDateInput,forex);

        String endDate = getEndDate();
        String startDate =  getStartDate(MONTH_DATE);
        ReqGraphDTO reqGraphDTO = new ReqGraphDTO(startDate,endDate,forex);

        try {
            Protocol.requestToServer(Protocol.TYPE_REQ_GRAPH,Protocol.CODE_REQ_GRAPH_MONTH,reqGraphDTO);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Protocol.receiveData();
    }

    public static void reqGraph3Month(){
        String title= StageStore.stage.getTitle();
        String forex =title.substring(0,3);
        Forex.setForex(txtDateInput,forex);

        String endDate = getEndDate();
        String startDate =  getStartDate(MONTH_3_DATE);
        ReqGraphDTO reqGraphDTO = new ReqGraphDTO(startDate,endDate,forex);

        try {
            Protocol.requestToServer(Protocol.TYPE_REQ_GRAPH,Protocol.CODE_REQ_GRAPH_3MONTH,reqGraphDTO);//
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Protocol.receiveData();
    }

    public static void reqGraphYear(){
        String title= StageStore.stage.getTitle();
        String forex =title.substring(0,3);

        String endDate = getEndDate();
        String startDate =  getStartDate(YEAR_DATE);
        ReqGraphDTO reqGraphDTO = new ReqGraphDTO(startDate,endDate,forex);

        try {
            Protocol.requestToServer(Protocol.TYPE_REQ_GRAPH,Protocol.CODE_REQ_GRAPH_YEAR,reqGraphDTO);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Protocol.receiveData();
    }
//////////////////////////응답
    public static void resSearchForex(byte[] data) throws IOException, ClassNotFoundException {

        try{
            ResSearchDTO resSearchDTO = (ResSearchDTO)Protocol.convertBytesToObject(data);
            String tmp = "bkpr : " +(resSearchDTO.getBkpr()).replace(",","")+"\n";
            tmp+="deal : " +(resSearchDTO.getDeal()).replace(",","")+"\n";
            tmp+="ttb : " +(resSearchDTO.getTtb()).replace(",","")+"\n";
            tmp+="tts : " +(resSearchDTO.getTts()).replace(",","");

            txtDateOutput.setText(tmp);
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Look, a Warning Dialog");
            alert.setContentText("조회할 수 없는 날짜를 입력하셨습니다(존재하지 않는 날짜 혹은 휴일).");
            alert.showAndWait();
            return;
        }


    }

    public static void resGraphMonth(byte[] data) throws IOException, ClassNotFoundException {
        ResGraphDTO resGraphDTO = (ResGraphDTO)Protocol.convertBytesToObject(data);
        ArrayList<String> arrayListPrice = resGraphDTO.getList();


        chart.getData().clear();
        list = FXCollections.observableArrayList();

        XYChart.Series series = new XYChart.Series();
        series.setName("Month Data");
        chartDrow(series,arrayListPrice);
    }

    public static void resGraph3Month(byte[] data) throws IOException, ClassNotFoundException {
        ResGraphDTO resGraphDTO = (ResGraphDTO)Protocol.convertBytesToObject(data);
        ArrayList<String> arrayListPrice = resGraphDTO.getList();


        chart.getData().clear();
        list = FXCollections.observableArrayList();

        XYChart.Series series = new XYChart.Series();
        series.setName("3Month Data");
        chartDrow(series,arrayListPrice);
    }

    public static void resGraphYear(byte[] data) throws IOException, ClassNotFoundException {
        ResGraphDTO resGraphDTO = (ResGraphDTO)Protocol.convertBytesToObject(data);
        ArrayList<String> arrayListPrice = resGraphDTO.getList();

        chart.getData().clear();
        list = FXCollections.observableArrayList();

        XYChart.Series series = new XYChart.Series();
        series.setName("Yer Data");
        chartDrow(series,arrayListPrice);
    }



    //날짜 구하기
    private static String getEndDate(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        DateFormat df = new SimpleDateFormat("yyyyMMdd");

        return df.format(cal.getTime());
    }

    private final static int MONTH_DATE = 1;
    private final static int YEAR_DATE = 2;
    private final static int MONTH_3_DATE = 3;

    private static String getStartDate(int dateType){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        DateFormat df = new SimpleDateFormat("yyyyMMdd");

        if(dateType == MONTH_DATE){
            cal.add(Calendar.MONTH, -1);
        }else if(dateType==YEAR_DATE){
            cal.add(Calendar.YEAR, -1);
        }else if(dateType==MONTH_3_DATE){
            cal.add(Calendar.MONTH, -3);
        }
        return df.format(cal.getTime());
    }

////차트 그리기
    private static void chartDrow(XYChart.Series series,ArrayList<String> arrayListPrice){

        double totalPrice = 0d;
        double max = Integer.MIN_VALUE;
        double min = Integer.MAX_VALUE;

        for (int i=0;i<arrayListPrice.size();i++){
            double price = Double.valueOf(arrayListPrice.get(i).replaceAll(",",""));//bkpr값
            if (price>max) max=price;
            if (price<min) min=price;
            series.getData().add(new XYChart.Data(i,price));
            totalPrice += price;

        }
        DecimalFormat form = new DecimalFormat("#.##");
        Double average= totalPrice/arrayListPrice.size();
        txtAverageOutput.setText("평균: "+form.format(average));//평균

        double dsum=0d;
        for(int i=0; i<arrayListPrice.size(); i++){
            double price = Double.valueOf(arrayListPrice.get(i).replaceAll(",",""));//bkpr값
            dsum += (price-average)*(price-average);
        }

        double sd = Math.sqrt(dsum/arrayListPrice.size());
        txtStandardDeviationOutput.setText("표준편차: "+form.format(sd));//표준편차

        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(min*99/100);
        yAxis.setUpperBound(max*100/99);
        list.addAll(series);
        chart.setData(list);
    }

}
