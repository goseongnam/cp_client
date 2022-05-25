package com.example.javafx_practice.item;

import javafx.scene.control.Alert;
import persistence.dto.ReqAlertDTO;
import persistence.dto.ResAlertDTO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class TimerAlert extends Thread {
    ArrayList<ReqAlertObject> timerArrList = new ArrayList<ReqAlertObject>();
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000); //여기서 쓰레드 작동시간을 조정
            } catch (Exception e) {
                e.printStackTrace();
            }
            read(); //파일에서 읽어와서 AlertObject를 arrayList에 채워넣음
            send(timerArrList);
//            receive();

        }
    }
    public void read() {
        ArrayList<String> aLines = null;
        //여기 부분에서 파일에서 읽어와서 arraylist에다가 추가해줘야 함.
        File alertFile = new File("C:\\fxfile\\Alert.txt");
        if (alertFile.exists()) {
            try {
                FileReader aReader = new FileReader(alertFile);
                BufferedReader aBufReader = new BufferedReader(aReader);
                aLines = new ArrayList<String>();
                String aLine = "";
                while ((aLine = aBufReader.readLine()) != null) {
                    aLines.add(aLine);
                }
                aBufReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int i = 0; i < aLines.size(); i++) {
                String deleteAlertArr = aLines.get(i);
                String[] tmpArray = deleteAlertArr.split(" ");
                String currencytmp = tmpArray[0];
                String alertAmount = tmpArray[1];
                //여기까지 currencytmp는 AED, alertAmount는 1000나오고 있음
                ReqAlertObject alertObject = new ReqAlertObject(currencytmp, alertAmount);
                timerArrList.add(alertObject);

            }
        }
    }

    private void send(ArrayList<ReqAlertObject> alertArr) {
        //여기 부분에서 alertArr을 서버로 보냄

        //alertArr.replaceAll(); 서버로 arrayㅣist를 보내고 나면 arrayList를 밀어버림. 이게 Thread가 작동할 때마다 빈 arrayList를 채워넣어야 하니까
    }
    public void receive(ResAlertDTO resAlertDTO) {
//        여기서 서버로부터 ResAlertDTO 즉, ResAlertObject가 담긴 arraylist를 받게됨.
//        그러면 서버로부터 받은 arraylist를 for문을 돌려서 하나씩 까보는데 judgement가 true 값이면 Alert를 띄워서 사용자에게 알리는거지

            for(int i=0; i<resAlertDTO.getResList().size(); i++) {
                if(resAlertDTO.getResList().get(i).isJudgement() == true) {
                    String currency_Alert = resAlertDTO.getResList().get(i).getCurrencytmp();
                    String amount_Alert = resAlertDTO.getResList().get(i).getAlertAmount();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("CONFIRMATION");
                    alert.setHeaderText("Look, a CONFIRMATION");
                    alert.setContentText(currency_Alert + " " + amount_Alert + "  설정금액에 도달하였습니다.");
                    alert.showAndWait();
            return;
                }
            }
    }

}
