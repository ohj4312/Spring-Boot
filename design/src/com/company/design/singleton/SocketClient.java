package com.company.design.singleton;

public class SocketClient {

    //null로 초기화하여 시작
    private static SocketClient socketClient=null;

    //기본생성자를 private으로 설정
    public SocketClient(){

    }

    //static 메소드이기 때문에 어떠한 클래스에서도 접근가능
    public static SocketClient getInstance(){
        //최초 한번만 객체생성
        if(socketClient==null){
            socketClient = new SocketClient();
        }
        return socketClient;
    }

    public void connect(){
        System.out.println("connect");
    }
}
