package com.example.javafx_practice;

import com.example.javafx_practice.item.Calculation;
import com.example.javafx_practice.item.Forex;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Protocol {

    public static Socket conn;
    public static DataOutputStream dos;
    public static DataInputStream dis;

    public static void connect(String ip, int port) throws IOException {
        conn = new Socket(ip,port);
        dos = new DataOutputStream(conn.getOutputStream());
        dis = new DataInputStream(conn.getInputStream());
    }

    //type
    public static final int TYPE_REQ_CALCULATE = 1;
    public static final int TYPE_REQ_GRAPH = 2;
    public static final int TYPE_REQ_ALERT = 3;
    public static final int TYPE_REQ_SEARCH = 4;

    public static final int TYPE_RES_CALCULATE = 11;
    public static final int TYPE_RES_GRAPH = 22;
    public static final int TYPE_RES_ALERT = 33;
    public static final int TYPE_RES_SEARCH = 44;

    //code
    //type -> 1
    public static final int CODE_REQ_CALCUALTE = 1;
    //type -> 2
    public static final int CODE_REQ_GRAPH_MONTH = 1;
    public static final int CODE_REQ_GRAPH_YEAR = 2;
    public static final int CODE_REQ_GRAPH_3MONTH = 3;
    //type -> 3
    public static final int CODE_REQ_ALERT_ = 0;
    //type -> 4
    public static final int CODE_REQ_SEARCH = 1;


    //type -> 11
    public static final int CODE_RES_CALCUALTE = 1;
    //type -> 22
    public static final int CODE_RES_GRAPH_MONTH = 1;
    public static final int CODE_RES_GRAPH_YEAR = 2;
    public static final int CODE_RES_GRAPH_3MONTH = 3;
    //type -> 33
    public static final int CODE_RES_ALERT_ = 0;
    //type -> 44
    public static final int CODE_RES_SEARCH = 1;





    //1바이트 -> 타입, 2바이트 -> 코드 , 3~6바이트 -> 데이터 길이 7~N -> 데이터
    private static byte[] paket;
    
    public static void requestToServer(int type, int code, Object data) throws IOException {
        paket = convertObjectToBytes(type,code,data);
        dos.write(paket);
    }
    public static void receiveData() {
        byte[] typeAndCode;
        try {
            typeAndCode = dis.readNBytes(2);

        int type = typeAndCode[0];
        int code = typeAndCode[1];
        byte[] byteSize = dis.readNBytes(4);
        int size = Protocol.byteToInt(byteSize);
        System.out.println("size : " + size);

        byte[] data = dis.readNBytes(size);

        switch (type){
            case TYPE_RES_CALCULATE:
                divCalculate(code, data);
                break;
            case TYPE_RES_GRAPH :
                divGraph(code,data);
                break;
            case TYPE_RES_ALERT :

                break;
            case TYPE_RES_SEARCH :
                divSearch(code,data);
                break;
        }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    //type 1
    private static void divCalculate(int code, byte[] data) throws IOException, ClassNotFoundException {
        switch (code){
            case CODE_RES_CALCUALTE:
                Calculation.resCalculate(data);
                break;
            case 2 :

        }
    }
    //type 2
    private static void divGraph(int code, byte[] data) throws IOException, ClassNotFoundException {
        switch (code){
            case CODE_RES_GRAPH_MONTH :
                Forex.resGraphMonth(data);
                break;
            case CODE_RES_GRAPH_YEAR:
                Forex.resGraphYear(data);
                break;
            case CODE_RES_GRAPH_3MONTH:
                Forex.resGraph3Month(data);
                break;
        }
    }

    //type 3
    private static void divAlert(int code, byte[] data) throws IOException, ClassNotFoundException {
        switch (code){
            case CODE_RES_ALERT_:

                break;
        }
    }
    //type 4
    private static void divSearch(int code, byte[] data) throws IOException, ClassNotFoundException {
        switch (code){
            case CODE_RES_SEARCH :
                Forex.resSearchForex(data);
                break;
        }
    }










//------------------------------------------------------------------------------------------------------
    private static byte[] convertObjectToBytes(int type, int code, Object obj) throws IOException {
        byte[] objByteArr;
        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        try (ObjectOutputStream ois = new ObjectOutputStream(boas)) {
            ois.writeObject(obj);
            objByteArr = boas.toByteArray(); //객체가 들어있음
        }
        paket = new byte[6+objByteArr.length];
        byte[] sizeArr = intToByte(objByteArr.length);
        paket[0] = (byte)type;
        paket[1] = (byte)code;
        for(int i=0;i<4;i++){
            paket[2+i] = sizeArr[i];
        }
        for(int i=0;i<objByteArr.length;i++){
            paket[6+i] = objByteArr[i];
        }
        return paket;
    }

    public static Object convertBytesToObject(byte[] bytes)
            throws IOException, ClassNotFoundException {
        InputStream is = new ByteArrayInputStream(bytes);
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return ois.readObject();
        }
    }



    //패킷 내 바이트 길이를 4바이트로 지정
    private static byte[] intToByte(int i){
        ByteBuffer buff = ByteBuffer.allocate(Integer.SIZE/8);
        buff.putInt(i);
        buff.order(ByteOrder.BIG_ENDIAN);
        return buff.array();

    }

    private static int byteToInt(byte[] bytes){
        byte[] newBytes = new byte[4];
        final int size = Integer.SIZE/8;
        ByteBuffer buff = ByteBuffer.allocate(size);
        for(int i =0;i<size;i++) {
            if (i +bytes.length<size) {
                newBytes[i] = (byte) 0x00;
            }
            else {
                newBytes[i] = bytes[i+bytes.length-size];
            }
        }
        buff = ByteBuffer.wrap(newBytes);
        buff.order(ByteOrder.BIG_ENDIAN);
        return buff.getInt();
    }

}