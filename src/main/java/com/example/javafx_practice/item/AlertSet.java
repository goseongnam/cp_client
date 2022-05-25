package com.example.javafx_practice.item;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AlertSet {
    private static int alertNum = 0;
    public static ArrayList<Object> timerArrList = new ArrayList<Object>();

    public static void alertClick(TextField txtAlertInput, String currencytmp, String alertAmount) throws IOException {
        System.out.println(txtAlertInput.getText());
        if (currencytmp == null || currencytmp.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("경고");
            alert.setHeaderText("Look, Warning");
            alert.setContentText("통화를 선택하지 않으셨습니다");
            alert.showAndWait();
            return;
        }
        String currency = currencytmp.substring(0, 3);
        alertAmount = txtAlertInput.getText(); //txtAlertInput은 입력 금액임
        if (alertAmount == null || alertAmount.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("경고");
            alert.setHeaderText("Look, Warning");
            alert.setContentText("설정금액을 입력하지 않으셨습니다");
            alert.showAndWait();
            return;
        }
        String AlertStackSet = currency + " " + alertAmount;

        String tmpArr[] = new String[]{"", "", "", "", "", "", "", "", "", ""};
        File file = new File("C:\\fxfile\\Alert.txt");
        String path = "C:\\fxfile"; //폴더 경로
        readFile_Alert(file, path, tmpArr);
        for (int i = 0; i < tmpArr.length; i++) {
            System.out.println(tmpArr[i]);
        }
        if (!checkDuplicate_Alert(tmpArr, AlertStackSet)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Look, a Warning Dialog");
            alert.setContentText("이미 Alert에 추가되어 있는 항목이거나 최대 등록개수를 초과했습니다.");
            alert.showAndWait();
            return;
        }
        // 중복체크
        else {
            FileOutputStream fos = new FileOutputStream(file, true);
            AlertStackSet += "\n";
            fos.write(AlertStackSet.getBytes());
            fos.close();

            //여기서 새로운 timer가 만들어져야 함.
            //여기서 AlertTimerObject를 만들어서 ArrayList에 채워넣음
            alertNum += 1; //식별숫자는 생성할때마다 계속늘어나는 거지.
            Timer alertTimer = new Timer(6000000, new ActionListener() {
                public void actionPerformed (ActionEvent e)
                {
                   //네트워크 보내는 코드-여기서 String currencytmp(선택통화), String alertAmount(입력가격)
                    //를 서버에게 보내고
                }
            });
            alertTimer.start();

            AlertTimerObject alertTimerObject = new AlertTimerObject(currencytmp, alertAmount, alertTimer);
            timerArrList.add(alertTimerObject);



            //여기 부분에 서버로부터 String currencytmp, String alertAmount, boolean judgement를 받아서 boolean값이 true인
            //경우에 여기서 알림창을 띄우는거지
        }
    }

    public static boolean checkDuplicate_Alert(String[] arr, String test) {
        if (arr[9]!=null&&!arr[9].equals("")) return false;//즐겨찾기 최대 개수 초과
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].equals("")) {
                count++;
            }//여기서 파일에서 읽어서 넣은 배열의 크기를 카운트
        }
        Set<String> set = new HashSet<String>(); // set of timeline

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("")||arr[i]==null) break;
            set.add(arr[i]);
        }
        set.add(test);
        if (count + 1 != set.size()) {
            return false;
        }
        return true;
    }

    public static void readFile_Alert(File file, String path, String[] arr) throws IOException {
        File Folder = new File(path);
        // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
        if (!Folder.exists()) {
            try {
                Folder.mkdir(); //폴더 생성합니다.
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        file.createNewFile();//파일 생성, 만약 이미 있으면 있는거 사용.

        FileReader filereader = new FileReader(file);
        BufferedReader bufReader = new BufferedReader(filereader);

        String line = "";
        int i = 0;
        while((line = bufReader.readLine()) != null){
            arr[i]=line;
            i++;
        }

        bufReader.close();
        filereader.close();
    }
}
