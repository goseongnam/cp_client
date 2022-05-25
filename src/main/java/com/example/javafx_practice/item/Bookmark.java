package com.example.javafx_practice.item;

import com.example.javafx_practice.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Bookmark {
    Button[] btnBookmarkArr= new Button[5];

    public Bookmark(Button[] BookmarkArr){
        for (int i=0;i<BookmarkArr.length;i++){
            btnBookmarkArr[i]=BookmarkArr[i];
        }
    }
    public void btnBookmarkAdd(String currencytmp) throws IOException {
        if(currencytmp == null) {
            return;
        }
        else {
            String arr[]=new String[]{"","","","",""};
            File file = new File("C:\\fxfile\\bookmark.txt");
            String path = "C:\\fxfile"; //폴더 경로
            readFile(file,path,arr);
            // 파일을 읽기

            if(!checkDuplicate(arr,currencytmp)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Look, a Warning Dialog");
                alert.setContentText("이미 즐겨찾기에 추가되어 있는 항목이거나 최대 등록개수를 초과했습니다.");

                alert.showAndWait();
            }
            // 중복체크
            else {
                writeFile(file,currencytmp);
                String arrForex[] = new String[5];
                readFile(file,path,arrForex);
                loadBookmark(arrForex,btnBookmarkArr);
                //파일에 쓰고 다시 읽어서 북마크 새로고침
            }
        }
    }

    public void loadBookmark(String[] arrForex, Button[] btnBookmarkArr){//화면에 즐겨찾기 된거 뿌려주기

        for(int i=0;i<5;i++){
            if (arrForex[i]==null) {
                String str = "즐겨찾기" + Integer.toString(i+1);
                btnBookmarkArr[i].setText(str);
            }
            else btnBookmarkArr[i].setText(arrForex[i]);
        }
    }


    private void writeFile(File file,String currencytmp) throws IOException {//스트링받아서 파일에 쓰기

        String currencyName = currencytmp + "\n";
        FileOutputStream fos =  new FileOutputStream(file,true);
        fos.write(currencyName.getBytes());
        fos.close();
    }
    private void writeFile(File file,String[] arr) throws IOException {//배열 받아서 파일에 쓰기
        new FileOutputStream(file).close();
        FileOutputStream fos =  new FileOutputStream(file,true);
        String item="";
        for (int i=0;i<arr.length;i++){
            if (arr[i]!=null&&!arr[i].equals("")) {
                item=arr[i]+"\n";
                fos.write(item.getBytes());
            }
        }
        fos.close();
    }

    public void readFile(File file, String path,String[] arr) throws IOException {//파일 읽기
        File Folder = new File(path);
        // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
        if (!Folder.exists()) {
            try{
                Folder.mkdir(); //폴더 생성합니다.
            }
            catch(Exception e){
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

    private boolean checkDuplicate(String[] arr,String test){//즐겨찾기 체킹

        if (!arr[4].equals("")) return false;//즐겨찾기 최대 개수 초과
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].equals("")){
                count++;
            }
        }
        Set<String> set = new HashSet<String>(); // set of timeline

        for (int i = 0; i < arr.length; i++) {
            if (arr[i]=="") break;
            set.add(arr[i]);
        }
        set.add(test);

        if (count+1!=set.size()){
            System.out.print("중복 발견");
            return false;
        }
        return true;

    }

    public void btnBookmarkDelete(String currencytmp) throws IOException {//삭제 버튼 클릭시 수행
        if (currencytmp==null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Look, a Warning Dialog");
            alert.setContentText("이미 삭제할 타겟이 존재하지 않습니다.");
            alert.showAndWait();
            return;
        }
        File file = new File("C:\\fxfile\\bookmark.txt");
        String path = "C:\\fxfile"; //폴더 경로
        String arrForex[] = new String[5];
        readFile(file,path,arrForex);
        String[] result = DeleteBookmark(arrForex,currencytmp);
        writeFile(file,result);
        for (int i=0;i<result.length;i++){
            System.out.println("arrForex[i]" + result[i]);
        }
        loadBookmark(result,btnBookmarkArr);
    }

    private String[] DeleteBookmark(String[] arrForex, String target){//내부적으로 호출되서 삭제 실행
        String[] result= new String[5];
        boolean success = false;
        for (int i=0;i<result.length;i++){
            if(arrForex[i]!=null&&arrForex[i].equals(target)) {
                for (int j=i; j+1<result.length;j++){
                    result[j]=arrForex[j+1];
                }
                success=true;
                break;
            }
            else result[i]=arrForex[i];
        }
        if (!success){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Look, a Warning Dialog");
            alert.setContentText("이미 삭제할 타겟이 존재하지 않습니다.");
            alert.showAndWait();
        }
        return result;
    }


    public void moveToBookmarkForex(ActionEvent actionEvent) throws IOException {//즐겨찾기된 외환화면으로 이동

        Button o = (Button)actionEvent.getSource();
        String name = o.getText();
        if (name.contains("즐")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Look, a Warning Dialog");
            alert.setContentText("즐겨찾기 추가되지 않은 항목은 이동할 수 없습니다");
            alert.showAndWait();
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Forex.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WindowSize.MAIN_X, 500);
        StageStore.stage.setTitle(name);
        StageStore.stage.setScene(scene);
        StageStore.stage.show();
    }

}