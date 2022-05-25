package com.example.javafx_practice.item;

import com.example.javafx_practice.Protocol;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import persistence.dto.ReqCalculationDTO;
import persistence.dto.ResCalculationDTO;

import java.io.IOException;

public class Calculation {
    private static String currencytmp_tmp;
    private static ChoiceBox choiceMethod;
    private static TextField txtExInput;
    private static TextField txtExOutput;

    public static void setCalculation(String a, ChoiceBox b, TextField c, TextField d){
        currencytmp_tmp = a;
        choiceMethod = b;
        txtExInput = c;
        txtExOutput = d;
    }

    public static void btnChk_Calculate() {
        if (currencytmp_tmp == null || currencytmp_tmp.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("경고");
            alert.setHeaderText("Look, Warning");
            alert.setContentText("통화를 선택하지 않으셨습니다");
            alert.showAndWait();
            return;
        }
        String seletedOption = (String) choiceMethod.getValue();
        if(seletedOption == null || seletedOption.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("경고");
            alert.setHeaderText("Look, Warning");
            alert.setContentText("옵션을 선택하지 않았습니다");
            alert.showAndWait();
            return;
        }
        String exchangeOption = seletedOption.substring(0, 3);
        String currencytmp = currencytmp_tmp.substring(0, 3);
        //BKPR이 4글자라서 서버에서 서버에 BKP가 날아온경우에 무엇을 보내도록 조건을 걸어야 할듯
        String inputCurrency = txtExInput.getText();
        if(inputCurrency == null || inputCurrency.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("경고");
            alert.setHeaderText("Look, Warning");
            alert.setContentText("입력값을 넣지않았습니다.");
            alert.showAndWait();
            return;
        }
        int currentExchange = Integer.parseInt(inputCurrency);
        //이 부분에 네트워크로 String currencytmp(선택통화), int currentExchange(환율계산입력값), String exchangeOption(파고살때 달라지는 옵션)
        //을 서버에게 보내고 해당하는 값을 반환받음
        ReqCalculationDTO calculationRequestDTO = new ReqCalculationDTO(currencytmp, currentExchange, exchangeOption);
        txtExOutput.setText(String.valueOf(currentExchange));

        try {
            Protocol.requestToServer(Protocol.TYPE_REQ_CALCULATE,Protocol.CODE_REQ_CALCUALTE,calculationRequestDTO);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Protocol.receiveData();
    }

    public static void resCalculate(byte[] data) throws IOException, ClassNotFoundException {
        ResCalculationDTO resCalculationDTO = (ResCalculationDTO)Protocol.convertBytesToObject(data);
        txtExOutput.setText(String.valueOf(resCalculationDTO.getResultExchange()));
    }




}